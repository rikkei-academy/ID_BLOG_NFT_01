package ProjectBlogOJT.controller;


import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @PermitAll
    @GetMapping("/getAll")
    public List<Blog> getAll(){
        return blogService.findAll();
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public Blog create(@RequestBody Blog blog){
        return blogService.saveOrUpdate(blog);
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("/update/{blogID}")
    public Blog update(@PathVariable("blogID") int blogID,@RequestBody Blog blog) {
        Blog blogUpdated =blogService.findByID(blogID);
        blogUpdated.setBlogTitle(blog.getBlogTitle());
        blogUpdated.setBlogContent(blog.getBlogContent());
        blogUpdated.setBlogImage(blog.getBlogImage());
        blogUpdated.setBlogCreateDate(blog.getBlogCreateDate());
        return blogService.saveOrUpdate(blogUpdated);
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("/delete/{blogID}")
    public ResponseEntity<?> delete(@PathVariable("blogID") int blogID){
        blogService.delete(blogID);
        return ResponseEntity.ok("Your blog has been deleted");
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/sortByCreateDate")
    public ResponseEntity<List<Blog>> sortByCreateDate(@RequestParam String direction){
        List<Blog> listBlog = blogService.sortByCreatedDate(direction);
        return new ResponseEntity<>(listBlog, HttpStatus.OK);
    }
 }
