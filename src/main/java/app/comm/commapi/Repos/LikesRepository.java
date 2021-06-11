package app.comm.commapi.Repos;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import app.comm.commapi.Models.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<List<Likes>> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Likes  WHERE postId = ?1 AND userId = ?2")
    void deleteLike(Long postId, Long userId);
}
