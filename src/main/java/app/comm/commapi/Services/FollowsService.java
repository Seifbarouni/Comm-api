package app.comm.commapi.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.comm.commapi.Models.Follows;
import app.comm.commapi.Models.User;
import app.comm.commapi.Repos.FollowsRepository;
import app.comm.commapi.Repos.UserRepository;

@Service
public class FollowsService {
    @Autowired
    private FollowsRepository followsRepository;
    @Autowired
    private UserRepository userRepository;

    public Boolean isFollowing(Long followerId, Long followedId) {
        Follows f = followsRepository.findFollow(followerId, followedId);
        if (f != null)
            return true;
        return false;
    }

    public String follow(Long followerId, Long followedId) {
        Follows testFollow = followsRepository.findFollow(followedId, followedId);
        if (testFollow == null) {
            testFollow = followsRepository.save(new Follows(followerId, followedId));
            if (testFollow != null) {
                Optional<User> follower = userRepository.findById(followerId);
                Optional<User> followed = userRepository.findById(followedId);
                if (followed.isEmpty() || follower.isEmpty())
                    return "Error";
                follower.get().setFollowing(follower.get().getFollowing() + 1L);
                followed.get().setFollowers(followed.get().getFollowers() + 1L);
                userRepository.save(follower.get());
                userRepository.save(followed.get());
                return "Success";
            }
        }
        return "Error";
    }
}
