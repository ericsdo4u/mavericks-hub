package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.dtos.request.AddMediaToPlaylistRequest;
import com.maverickshube.maverickshube.dtos.request.CreatePlaylistRequest;
import com.maverickshube.maverickshube.dtos.request.ShufflePlaylistRequest;
import com.maverickshube.maverickshube.dtos.response.AddMediaToPlaylistResponse;
import com.maverickshube.maverickshube.dtos.response.CreatePlaylistResponse;
import com.maverickshube.maverickshube.dtos.response.MediaResponse;
import com.maverickshube.maverickshube.dtos.response.ShufflePlaylistResponse;
import com.maverickshube.maverickshube.exceptions.PlaylistNotFoundException;
import com.maverickshube.maverickshube.exceptions.UserNotFoundException;
import com.maverickshube.maverickshube.models.Media;
import com.maverickshube.maverickshube.models.Playlist;
import com.maverickshube.maverickshube.models.User;
import com.maverickshube.maverickshube.repositories.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class MavericksHubPlaylistService implements PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final MediaService mediaService;

    @Override
    public CreatePlaylistResponse create(CreatePlaylistRequest request) throws UserNotFoundException {
        Playlist newPlaylist = modelMapper.map(request, Playlist.class);
        User uploader = userService.getUserById(request.getUserId());
        newPlaylist.setUploader(uploader);
        Playlist savedPlaylist = playlistRepository.save(newPlaylist);
        var response = modelMapper.map(savedPlaylist, CreatePlaylistResponse.class);
        response.setMessage("Playlist created successfully");
        return response;
    }

    @Override
    public Playlist getPlaylistBy(Long id) throws PlaylistNotFoundException {
        return playlistRepository.findById(id)
                .orElseThrow(() -> new PlaylistNotFoundException("Playlist not found"));
    }

    @Override
    public AddMediaToPlaylistResponse addMediaToPlaylist(AddMediaToPlaylistRequest addMediaRequest) throws PlaylistNotFoundException {
        Playlist playlist = getPlaylistBy(addMediaRequest.getPlaylistId());
        Media media = mediaService.getMediaById(addMediaRequest.getMediaId());
        playlist.getMedia().add(media);
        media.getPlaylist().add(playlist);
        playlist = playlistRepository.save(playlist);

        return modelMapper.map(playlist, AddMediaToPlaylistResponse.class);
    }

    @Override
    public ShufflePlaylistResponse shuffle(ShufflePlaylistRequest request) throws PlaylistNotFoundException {
        Playlist playlist = getPlaylistBy(request.getPlaylistId());
        List<Media> media = mediaService.getMediaForPlaylist(playlist.getId());
        Collections.shuffle(media);
        var response = modelMapper.map(media, ShufflePlaylistResponse.class);
        List<MediaResponse> mediaResponse = List.of(modelMapper.map(media, MediaResponse[].class));
        response.setMedia(mediaResponse);
        return response;
    }

    @Override
    public List<MediaResponse> getAllMedia(Long playlistId) throws PlaylistNotFoundException {
        List<Media> media = mediaService.getMediaForPlaylist(playlistId);
        return List.of(modelMapper.map(media, MediaResponse[].class));
    }
}