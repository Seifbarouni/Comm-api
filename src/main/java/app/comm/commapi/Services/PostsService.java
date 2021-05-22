package app.comm.commapi.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.comm.commapi.Models.Post;

import app.comm.commapi.Repos.PostsRepository;

@Service
public class PostsService {
    @Autowired
    private PostsRepository postsRepository;

    public String savePost(Post post) {
        Post newPost = postsRepository.save(post);
        if (newPost != null)
            return "Success";
        return "Error";
    }

    public byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed  Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }

    public List<Post> getPostsByCommunity(String community) {
        Optional<List<Post>> posts = postsRepository.findByCommunity(community);
        List<Post> res = new ArrayList<>();
        if (posts.isPresent() && !posts.get().isEmpty()) {
            res = posts.get();
            for (Post post : res) {
                if (post.getImage() != null)
                    post.setImage(decompressBytes(post.getImage()));
                else if (post.getVideo() != null)
                    post.setVideo(decompressBytes(post.getVideo()));
            }
        }
        return res;
    }

    public Post getPostById(Long id) {
        Optional<Post> post = postsRepository.findById(id);
        if (post.isPresent()) {
            Post p = post.get();
            if (p.getImage() != null)
                p.setImage(decompressBytes(p.getImage()));
            else if (p.getVideo() != null)
                p.setVideo(decompressBytes(p.getVideo()));
            return p;
        }
        return null;
    }
}
