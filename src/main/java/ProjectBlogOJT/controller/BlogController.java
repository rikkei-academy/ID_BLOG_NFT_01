package ProjectBlogOJT.controller;


import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.entity.Tag;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.BlogService;
import ProjectBlogOJT.model.service.TagService;
import ProjectBlogOJT.model.service.UserService;
import ProjectBlogOJT.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/blog")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @Autowired
    TagService tagService;

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/getAll")
    public List<Blog> getAll(){
        return blogService.findAll();
    }
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostMapping("/create")
    public Blog create(@RequestBody Blog blog ){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByID(userDetails.getUserId());
        List<Tag> listTag = new ArrayList<>();
        for (Tag tag:blog.getListTag()) {
            if(tagService.findByTagName(tag.getTagName())!=null){
                listTag.add(tag);
            }else {
                Tag tagNew = new Tag();
                tagNew.setTagName(tag.getTagName());
                tagNew.setTagStatus(true);
                listTag.add(tagService.save(tagNew));
            }
        }
        blog.setListTag(listTag);
        blog.setUser(user);
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
    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/searchByCreateDate")
    public ResponseEntity<Map<String, Object>> findByCreateDateAndSort(
            @RequestParam String from,
            @RequestParam String to,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam String direction
    ){
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"blogCreateDate");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"blogCreateDate");
        }
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);

        Pageable pageable = PageRequest.of(page,2,Sort.by(order));
        Page<Blog> pageBlog = blogService.findByBlogCreateDateBetweenAndSort(fromDate,toDate,pageable);
        Map<String,Object> data = new HashMap<>();
        data.put("blogs",pageBlog.getContent());
        data.put("total",pageBlog.getSize());
        data.put("totalItems",pageBlog.getTotalElements());
        data.put("totalPages",pageBlog.getTotalPages());
        return  new ResponseEntity<>(data,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MODERATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("/findByUser")
    public List<Blog> findByUser(@RequestParam String fullName){
        List<User> listUser = userService.findByFullName(fullName);
        List<Blog> listAllBlog = new ArrayList<>();
        for (User user:listUser) {
            List<Blog> listBlog = blogService.findByUserID(user.getUserID());
            listAllBlog.addAll(listBlog);
        }
        return listAllBlog;
    }
 }
