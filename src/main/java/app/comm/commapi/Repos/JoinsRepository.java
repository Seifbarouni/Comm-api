package app.comm.commapi.Repos;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import app.comm.commapi.Models.Joins;

public interface JoinsRepository extends JpaRepository<Joins, Long> {
    Optional<List<Joins>> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Joins  WHERE userId = ?1 AND communityId = ?2")
    void deleteJoin(Long userId, Long communityId);
}
