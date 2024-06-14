package com.maverickshube.maverickshube.repositories;



import com.maverickshube.maverickshube.models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
//    @Query("SELECT m FROM Playlist m where m.uploader.id=:userId")
//    List<Media> findAllPlaylistFor(Long userId);

}