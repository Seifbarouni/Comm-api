package app.comm.commapi.Repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.comm.commapi.Models.Community;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByName(String name);
}
