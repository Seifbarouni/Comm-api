package app.comm.commapi.Repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.comm.commapi.Models.Follows;

public interface FollowsRepository extends JpaRepository<Follows, Long> {

    @Query("SELECT f FROM Follows f WHERE f.followerId=:followerId AND f.followedId=:followedId")
    Follows findFollow(@Param("followerId") Long followerId, @Param("followedId") Long followedId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Follows  WHERE followerId = ?1 AND followedId = ?2")
    void deleteJoin(Long followerId, Long followedId);
}
