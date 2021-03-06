package app.comm.commapi.Repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import app.comm.commapi.Models.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    @Query("SELECT f FROM Follows f WHERE f.followerId=?1 AND f.followedId=?2")
    Follows findFollow(Long followerId, Long followedId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follows  WHERE followerId = ?1 AND followedId = ?2")
    void deleteFollow(Long followerId, Long followedId);
}
