package app.comm.commapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import app.comm.commapi.Services.FollowsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class FollowsController {
    @Autowired
    private FollowsService followsService;

    @GetMapping("/isFollowing/{followerId}/{followedId}")
    public Boolean isFollowing(@PathVariable(name = "followerId") Long followerId,
            @PathVariable(name = "followedId") Long followedId) {
        return followsService.isFollowing(followerId, followedId);
    }

    @GetMapping("/follow/{followerId}/{followedId}")
    public String follow(@PathVariable(name = "followerId") Long followerId,
            @PathVariable(name = "followedId") Long followedId) {
        return followsService.follow(followerId, followedId);
    }
}
