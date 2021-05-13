package app.comm.commapi.Repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.comm.commapi.Models.Joins;

public interface JoinsRepository extends JpaRepository<Joins, Long> {
    Optional<List<Joins>> findByUserId(Long userId);
}
