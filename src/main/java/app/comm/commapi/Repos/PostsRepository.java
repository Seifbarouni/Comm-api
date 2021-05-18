package app.comm.commapi.Repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import app.comm.commapi.Models.Post;

public interface PostsRepository extends JpaRepository<Post, Long> {
    @Query("SELECT n FROM Post n WHERE n.user=?1 AND n.community=?2")
    List<Post> findPosts(String user, String community);
}
