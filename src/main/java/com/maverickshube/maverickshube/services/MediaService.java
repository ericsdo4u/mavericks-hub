package com.maverickshube.maverickshube.services;


import com.github.fge.jsonpatch.JsonPatch;
import com.maverickshube.maverickshube.dtos.*;
import com.maverickshube.maverickshube.models.Media;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MediaService {
    UploadMediaResponse upload(UploadMediaRequest request);

    Media getMediaById(long id);
    UpdateMediaResponse updateMedia(Long mediaId, JsonPatch jsonPatch);

    List<MediaResponse> getMediaFor(Long userId);

    List<Media> getMediaForPlaylist(Long playlistId);

}
