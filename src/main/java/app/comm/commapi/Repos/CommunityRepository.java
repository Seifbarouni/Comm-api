package app.comm.commapi.Repos;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.comm.commapi.Models.Community;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    Optional<Community> findByName(String name);

    @Query("SELECT n FROM Community n WHERE n.id NOT IN :ids")
    List<Community> findExploreSection(@Param("ids") Collection<Long> ids);

}
