package com.maverickshube.maverickshube.controllers;

import com.github.fge.jsonpatch.JsonPatch;
import com.maverickshube.maverickshube.dtos.request.UploadMediaRequest;
import com.maverickshube.maverickshube.dtos.response.UploadMediaResponse;
import com.maverickshube.maverickshube.services.MediaService;
import lombok.AllArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/v1/media")
@AllArgsConstructor
public class MediaController {

    private final MediaService mediaService;
    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UploadMediaResponse>uploadMedia(@ModelAttribute UploadMediaRequest request){
        return ResponseEntity.status(CREATED)
                .body(mediaService.upload(request));
    }

    @GetMapping
    public ResponseEntity<?> getMediaForUser(@RequestParam Long userId){
        return ResponseEntity.ok(mediaService.getMediaFor(userId));
    }
    @PatchMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
        public ResponseEntity<?> updateMedia(@ModelAttribute Long mediaId, @ModelAttribute JsonPatch jsonPatch){
        return ResponseEntity.status(CREATED).body(mediaService.updateMedia(mediaId, jsonPatch));
    }

}
