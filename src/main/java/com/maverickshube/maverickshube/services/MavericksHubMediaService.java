package com.maverickshube.maverickshube.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Uploader;

import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.maverickshube.maverickshube.dtos.*;
import com.maverickshube.maverickshube.exceptions.MediaUpdateFailException;
import com.maverickshube.maverickshube.exceptions.MediaUploadFailedException;
import com.maverickshube.maverickshube.exceptions.PlaylistNotFoundException;
import com.maverickshube.maverickshube.models.Media;
import com.maverickshube.maverickshube.models.User;
import com.maverickshube.maverickshube.repositories.MediaRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MavericksHubMediaService implements MediaService {

    private final MediaRepository mediaRepository;

    private final Cloudinary cloudinary;
    private final ModelMapper modelMapper;
    private final UserService userService;

    @Override
    public UploadMediaResponse upload(UploadMediaRequest request) {
        User user = userService.getUserById(request.getUserId());
        try {
            Uploader uploader = cloudinary.uploader();
            Map<?, ?> response = uploader.upload(request.getMediaFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto"));
            String url = response.get("url").toString();
            Media media = modelMapper.map(request, Media.class);
            media.setUrl(url);
            media.setId(user.getId());
            media = mediaRepository.save(media);
            return modelMapper.map(media, UploadMediaResponse.class);
        }catch (IOException exception){
            throw new MediaUploadFailedException("Failed to upload");
        }
    }

    @Override
    public Media getMediaById(long id) {
        return mediaRepository.findById(id)
                .orElseThrow(()->new MediaNotFoundException(String.format("media with this id %d not found", id)));
    }

    @Override
    public UpdateMediaResponse updateMedia(Long mediaId, JsonPatch jsonPatch) {
        try {
            //1. get target object
            Media media = getMediaById(mediaId);
            //2. convert object from above to JsonNode (use ObjectMapper)
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode mediaNode = objectMapper.convertValue(media, JsonNode.class);
            //3. apply jsonPatch to mediaNode
            mediaNode = jsonPatch.apply(mediaNode);
            //4. convert mediaNode to media object
            media = objectMapper.convertValue(mediaNode, Media.class);
            media = mediaRepository.save(media);
            return modelMapper.map(media, UpdateMediaResponse.class);
        }catch (JsonPatchException exception){
            throw new MediaUpdateFailException(exception.getMessage());
        }
    }
    @Override
    public List<MediaResponse> getMediaFor(Long userId) {
        List<Media> media = mediaRepository.findAllMediaFor(userId);
        return media.stream()
                .map(mediaItem -> modelMapper.map(mediaItem, MediaResponse.class)).toList();
    }

    @Override
    public List<Media> getMediaForPlaylist(Long playlistId) throws PlaylistNotFoundException {
        return mediaRepository.findByPlaylistId(playlistId);
    }
}
