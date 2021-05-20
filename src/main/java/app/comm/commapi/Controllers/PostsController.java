package app.comm.commapi.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import app.comm.commapi.Models.PostInput;
import app.comm.commapi.Services.PostsService;

@RestController
@CrossOrigin(origins = "http://localhost:5000", exposedHeaders = "**")
public class PostsController {
    @Autowired
    private PostsService postsService;

    @PostMapping("/p/addPost")
    public String addPost(@RequestParam(name = "file", required = false) @Nullable MultipartFile file,
            @RequestParam(name = "postInput", required = true) PostInput postInput) {
        System.out.println(file.getContentType());
        postsService.savePost(file, postInput);
        return "";
    }

}
