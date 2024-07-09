package com.maverickshube.maverickshube.services;

import com.maverickshube.maverickshube.dtos.request.AddMediaToPlaylistRequest;
import com.maverickshube.maverickshube.dtos.request.CreatePlaylistRequest;
import com.maverickshube.maverickshube.dtos.response.MediaResponse;
import com.maverickshube.maverickshube.dtos.request.ShufflePlaylistRequest;
import com.maverickshube.maverickshube.exceptions.PlaylistNotFoundException;
import com.maverickshube.maverickshube.exceptions.UserNotFoundException;
import com.maverickshube.maverickshube.models.Media;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;


import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@Sql(scripts = {"/db/data.sql"})
public class PlaylistServiceTest {

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private MediaService mediaService;


    @Test
    public void testCreatePlaylist() throws UserNotFoundException, PlaylistNotFoundException {
        CreatePlaylistRequest request = new CreatePlaylistRequest();
        request.setName("name");
        request.setDescription("description");
        request.setUserId(200L);
        var response = playlistService.create(request);
        System.out.println(response);
        assertThat(response).isNotNull();
        assertThat(response.getMessage()).contains("success");
    }


    @Test
    public void addMediaToPlaylist() throws PlaylistNotFoundException {
        AddMediaToPlaylistRequest addMediaRequest = new AddMediaToPlaylistRequest();
        addMediaRequest.setPlaylistId(300L);
        addMediaRequest.setMediaId(102L);
        Media media = mediaService.getMediaById(102L);
        System.out.println("before: "+media.getPlaylist().size());

        var response = playlistService.addMediaToPlaylist(addMediaRequest);
        System.out.println(response);
        media = mediaService.getMediaById(102L);
        System.out.println("after: "+media.getPlaylist().size());
        assertThat(response).isNotNull();
        assertThat(response.getMedia().size()).isEqualTo(4);
    }

    @Test
    public void shufflePlaylistTest() throws PlaylistNotFoundException {
        Long playlistId = 300L;
        ShufflePlaylistRequest request = new ShufflePlaylistRequest();
        request.setPlaylistId(playlistId);
        var response = playlistService.shuffle(request);

        List<MediaResponse> originalMediaList = playlistService.getAllMedia(playlistId);
        List<MediaResponse> shuffledMediaList = response.getMedia();
        System.out.println("Original Media List: " + originalMediaList);
        System.out.println("Shuffled Media List: " + shuffledMediaList);

        assertNotEquals(originalMediaList, shuffledMediaList, "The shuffled media list should not be the same as the original media list.");
    }


}