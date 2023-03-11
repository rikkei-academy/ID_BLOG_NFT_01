package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.BlogService;
import ProjectBlogOJT.model.service.CommentService;
import ProjectBlogOJT.model.service.UserSevice;
import ProjectBlogOJT.payload.request.CommentCreate;
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
@RequestMapping("/api/v1/comment")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserSevice userSevice;
    @Autowired
    BlogService blogService;
    @GetMapping()
    public List<Comment> productList() {
        List<Comment> commentList = commentService.findAll();
        return commentList;
    }
    @PostMapping("/delete/{commentID}")
    public Comment delete(@PathVariable("commentID") int commentID) {
        Comment comment = commentService.findByID(commentID);
        if (comment.isCommentStatus() == true) {
            comment.setCommentStatus(false);
        } else {
            comment.setCommentStatus(true);
        }
        return commentService.saveOrUpdate(comment);
    }
    @PostMapping
    public Comment createComment(@RequestBody CommentCreate commentCreate) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = userSevice.findByID(userDetails.getUserId());
        Blog blog = blogService.findByID(commentCreate.getBlogID());
        Comment comment = new Comment();
        comment.setCommentDate(commentCreate.getCommentDate());
        comment.setCommentContent(commentCreate.getCommentContent());
        comment.setCommentStatus(true);
        comment.setUser(users);
        comment.setBlog(blog);
        return commentService.saveOrUpdate(comment);
    }

    @PutMapping("/update/{commentID}")
    public Comment updateComment(@PathVariable("commentID") int commentID, @RequestBody Comment comment) {
        Comment commentUpdate = commentService.findByID(commentID);
        commentUpdate.setCommentContent(comment.getCommentContent());
        commentUpdate.setCommentDate(java.time.LocalDate.now());
        return commentService.saveOrUpdate(commentUpdate);
    }
    @GetMapping("/sortBetween")
    public ResponseEntity<Map<String, Object>> listComment(@RequestParam String form,
                                                           @RequestParam String to,
                                                           @RequestParam(defaultValue = "0") int page,
                                                           @RequestParam String direction){
        LocalDate fromDate = LocalDate.parse(form);
        LocalDate toDate = LocalDate.parse(to);
        Sort.Order order;
        if (direction.equals("asc")){
            order=new Sort.Order(Sort.Direction.ASC,"commentDate");
        }else{
            order=new Sort.Order(Sort.Direction.DESC,"commentDate");
        }
        Pageable pageable = PageRequest.of(page, 2,Sort.by(order));
        Page<Comment> pageComment = commentService.sortBetween(fromDate,toDate,pageable);
        Map<String, Object> data = new HashMap<>();
        data.put("comment", pageComment.getContent());
        data.put("total", pageComment.getSize());
        data.put("totalItems", pageComment.getTotalElements());
        data.put("totalPages", pageComment.getTotalPages());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }




}
