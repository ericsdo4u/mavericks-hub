package com.maverickshube.maverickshube.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMediaToPlaylistRequest {
    private Long playlistId;
    private Long mediaId;
}