package app.comm.commapi.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import app.comm.commapi.Models.Likes;
import app.comm.commapi.Models.Post;
import app.comm.commapi.Repos.LikesRepository;
import app.comm.commapi.Repos.PostsRepository;

@Service
public class LikesService {
    private LikesRepository likesRepository;
    private PostsRepository postsRepository;

    public List<Likes> getLikesByUserId(Long userId) {
        Optional<List<Likes>> optLikes = likesRepository.findByUserId(userId);
        if (optLikes.isPresent())
            return optLikes.get();
        return new ArrayList<Likes>();
    }

    public String likePost(Likes likes) {
        Optional<Post> post = postsRepository.findById(likes.getPostId());
        if (post.isPresent()) {
            likesRepository.save(likes);
            Post res = post.get();
            res.setLikes(res.getLikes() + 1);
            res = postsRepository.save(res);
            if (res != null)
                return "Success";
            return "Error";
        }
        return "Error";
    }

    public String dislikePost(Likes likes) {
        Optional<Post> post = postsRepository.findById(likes.getPostId());
        if (post.isPresent()) {
            likesRepository.delete(likes);
            Post res = post.get();
            res.setLikes(res.getLikes() - 1);
            res = postsRepository.save(res);
            if (res != null)
                return "Success";
            return "Error";
        }
        return "Error";
    }
}
