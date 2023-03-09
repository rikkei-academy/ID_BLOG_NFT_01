package ProjectBlogOJT.controller;


import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    @Autowired
    BlogService blogService;

    @GetMapping("/getAll")
    public List<Blog> getAll(){
        return blogService.findAll();
    }

    @PostMapping("/create")
    public Blog create(@RequestBody Blog blog){
        return blogService.saveOrUpdate(blog);
    }
}
