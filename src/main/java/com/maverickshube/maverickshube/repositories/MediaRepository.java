package com.maverickshube.maverickshube.repositories;

import com.maverickshube.maverickshube.models.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

    @Query("SELECT m FROM Media m where m.uploader.id=:userId")
    List<Media> findAllMediaFor(Long userId);

    List<Media> findByPlaylistId(Long playlistId);
}
