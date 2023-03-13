package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Likes;
import ProjectBlogOJT.model.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    @Autowired
    LikeService likeService;

    @GetMapping()
    public List<Likes> getAll(){
        return likeService.findAll();
    }

    @PostMapping()
    public Likes createLike(@RequestBody Likes likes){
        return likeService.save(likes);
    }
    @PostMapping("deleteLikes/{likeID}")
    public Likes createLike(@PathVariable("likeID") int likes){
        Likes likesDelete = likeService.findByID(likes);
        likesDelete.setLikeStatus(false);
        return likeService.save(likesDelete);
    }




}

