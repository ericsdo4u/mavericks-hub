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
import com.maverickshube.maverickshube.models.Playlist;

import java.util.List;

public interface PlaylistService {
    CreatePlaylistResponse create(CreatePlaylistRequest request) throws UserNotFoundException;

    Playlist getPlaylistBy(Long id) throws PlaylistNotFoundException;

     AddMediaToPlaylistResponse addMediaToPlaylist(AddMediaToPlaylistRequest addMediaRequest) throws PlaylistNotFoundException;

    ShufflePlaylistResponse shuffle(ShufflePlaylistRequest request) throws PlaylistNotFoundException;

    List<MediaResponse> getAllMedia(Long playlistId) throws PlaylistNotFoundException;
}