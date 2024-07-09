package com.maverickshube.maverickshube.services;


import com.github.fge.jsonpatch.JsonPatch;
import com.maverickshube.maverickshube.dtos.request.UploadMediaRequest;
import com.maverickshube.maverickshube.dtos.response.MediaResponse;
import com.maverickshube.maverickshube.dtos.response.UpdateMediaResponse;
import com.maverickshube.maverickshube.dtos.response.UploadMediaResponse;
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
