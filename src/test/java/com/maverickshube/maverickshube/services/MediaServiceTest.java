package com.maverickshube.maverickshube.services;

import com.fasterxml.jackson.databind.node.TextNode;
import com.github.fge.jackson.jsonpointer.JsonPointer;
import com.github.fge.jackson.jsonpointer.JsonPointerException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchOperation;
import com.github.fge.jsonpatch.ReplaceOperation;

import com.maverickshube.maverickshube.dtos.response.MediaResponse;
import com.maverickshube.maverickshube.dtos.response.UpdateMediaResponse;
import com.maverickshube.maverickshube.dtos.request.UploadMediaRequest;
import com.maverickshube.maverickshube.dtos.response.UploadMediaResponse;
import com.maverickshube.maverickshube.models.Category;
import com.maverickshube.maverickshube.models.Media;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.jdbc.Sql;


import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.maverickshube.maverickshube.models.Category.ACTION;
import static com.maverickshube.maverickshube.models.Category.STEP_MOM;
import static com.maverickshube.maverickshube.utils.TestUtils.TEST_VIDEO_LOCATION;

import static com.maverickshube.maverickshube.utils.TestUtils.buildUploadMediaRequest;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
public class MediaServiceTest {
    @Autowired
    private MediaService mediaService;

    @Test
    public void test_uploadMedia() {
        Path path = Paths.get(TEST_VIDEO_LOCATION);
        try (var inputStream = Files.newInputStream(path)) {
            UploadMediaRequest request = buildUploadMediaRequest(inputStream);
            UploadMediaResponse response = mediaService.upload(request);
            log.info("response -> {}", response);
            assertThat(response).isNotNull();
            assertThat(response.getUrl()).isNotNull();
        } catch (IOException exception) {
            assertThat(exception).isNotNull();
        }
    }

    @Test
    @DisplayName("test update media files")
    @Sql(scripts = {"/db/data.sql"})
    public void updateMediaTest()throws JsonPointerException {
        Category category = mediaService.getMediaById(101).getCategory();
        assertThat(category).isNotEqualTo(ACTION);
        List<JsonPatchOperation> operations = java.util.List.of(
                new ReplaceOperation(new JsonPointer("/category"),
                    new TextNode(STEP_MOM.name()))
        );
        JsonPatch updateMediaRequest = new JsonPatch(operations);
        UpdateMediaResponse response = mediaService.updateMedia(101L, updateMediaRequest);
        assertThat(response).isNotNull();
        category = mediaService.getMediaById(101L).getCategory();
        assertThat(category).isEqualTo(STEP_MOM);
    }
    @Test
    @DisplayName("test that user can retrieve media by id")
    @Sql(scripts = {"/db/data.sql"})
    public void getMediaByIdTest(){
        Media media = mediaService.getMediaById(100L);
        log.info("media -> {}", media);
        assertThat(media).isNotNull();
    }

    @Test
    @Sql(scripts = {"/db/data.sql"})
    public void getMediaForUserTest(){
        Long userId = 200L;
        List<MediaResponse> mediaList = mediaService.getMediaFor(userId);
        assertThat(mediaList).hasSize(3);
    }
}