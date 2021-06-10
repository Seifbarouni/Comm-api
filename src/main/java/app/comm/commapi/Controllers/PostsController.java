package app.comm.commapi.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.comm.commapi.Models.Post;
import app.comm.commapi.Services.PostsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @PostMapping(value = "/p/addPost/{community}/{user}/{createdAt}/{text}", consumes = "multipart/form-data")
    @Caching(evict = { @CacheEvict(value = "postsByCommName", key = "#root.args[0]"),
            @CacheEvict(value = "postsByUsrId", key = "#root.args[1]") })
    public String addPost(@RequestPart @Nullable MultipartFile file, @PathVariable(name = "community") String community,
            @PathVariable(name = "user") String user, @PathVariable(name = "createdAt") String createdAt,
            @PathVariable(name = "text") String text) {
        Post post = new Post();
        post.setComments(0L);
        post.setLikes(0L);
        post.setCommunity(community);
        post.setUser(user);
        post.setTextContent(text);
        post.setCreatedAt(createdAt);

        if (file != null) {
            if (file.getContentType().contains("image")) {
                try {
                    post.setImage(postsService.compressBytes(file.getBytes()));
                } catch (Exception e) {
                    return "Error";
                }
            } else if (file.getContentType().contains("video")) {
                try {
                    post.setVideo(postsService.compressBytes(file.getBytes()));
                } catch (Exception e) {
                    return "Error";
                }
            }
        } else {
            post.setImage(null);
            post.setVideo(null);
        }
        return postsService.savePost(post);
    }

    @GetMapping("/p/getPosts/{community}")
    @Cacheable(value = "postsByCommName", key = "#root.args[0]")
    public List<Post> getPostsByCommunity(@PathVariable(name = "community") String community) {
        return postsService.getPostsByCommunity(community);
    }

    @GetMapping("/p/getPostsByUser/{user}")
    @Cacheable(value = "postsByUsrId", key = "#root.args[0]")
    public List<Post> getPostsByUser(@PathVariable(name = "user") String user) {
        return postsService.getPostsByUser(user);
    }

    @GetMapping("/p/getPost/{id}")
    public Post getPostById(@PathVariable(name = "id") Long id) {
        return postsService.getPostById(id);
    }

    @GetMapping("/p/likePost/{id}")
    public String likePost(@PathVariable(name = "id") Long id) {
        return postsService.likePost(id);
    }

    @GetMapping("/p/dislikePost/{id}")
    public String dislikePost(@PathVariable(name = "id") Long id) {
        return postsService.dislikePost(id);
    }

}
