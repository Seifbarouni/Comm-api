package app.comm.commapi.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Models.Likes;
import app.comm.commapi.Services.LikesService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class LikesController {
    private LikesService likesService;

    @GetMapping("/l/getLikes/{id}")
    public List<Likes> getLikesByUserId(@PathVariable(name = "id") Long userId) {
        return likesService.getLikesByUserId(userId);
    }

    @GetMapping("/l/like/{postId}/{userId}")
    public String like(@PathVariable(name = "postId") Long postId, @PathVariable(name = "userId") Long userId) {
        return likesService.likePost(new Likes(postId, userId));
    }

    @GetMapping("/l/dislike/{postId}/{userId}")
    public String dislike(@PathVariable(name = "postId") Long postId, @PathVariable(name = "userId") Long userId) {
        return likesService.dislikePost(new Likes(postId, userId));
    }
}
