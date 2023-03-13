package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.entity.Following;
import ProjectBlogOJT.model.entity.Likes;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.FollowingService;
import ProjectBlogOJT.model.service.UserService;
import ProjectBlogOJT.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/follow")
public class FollowController {
    @Autowired
    FollowingService followingService;

    @Autowired
    UserService userService;

    @GetMapping()
    public List<Following> getAll(){
        return followingService.findAll();
    }

    @PostMapping()
    public Following createFollow(@RequestBody Following following){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByID(userDetails.getUserId());
        following.setUser(user);
        following.setFollowStatus(true);
        return followingService.save(following);
    }
    @PostMapping("deleteFollowing/{followID}")
    public Following deleteFollow(@PathVariable("followID") int followID){
        Following following = followingService.findByID(followID);
        following.setFollowStatus(false);
        return followingService.save(following);
    }


}
