package com.maverickshube.maverickshube.repositories;

import com.maverickshube.maverickshube.models.Media;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
@Slf4j
@Sql(scripts = {"/db/data.sql"})
class MediaRepositoryTest {

    @Autowired
    private MediaRepository mediaRepository;

    @Test
    public void findAllMediaFor() {
        List<Media> media = mediaRepository.findAllMediaFor(200L);
        log.info("items->{}", media);
        assertThat(media).hasSize(3);
    }

}