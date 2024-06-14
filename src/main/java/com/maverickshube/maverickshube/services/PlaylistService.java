package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.dtos.*;
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