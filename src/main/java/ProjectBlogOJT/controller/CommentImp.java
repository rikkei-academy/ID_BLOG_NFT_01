package ProjectBlogOJT.controller;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1/comment")
public class CommentImp {
    @Autowired
    CommentService commentService;
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
    public Comment createComment(@RequestBody Comment comment) {
        Comment commentCreate = commentService.save(comment);
        return commentCreate;
    }

    @PutMapping("/update/{commentID}")
    public Comment updateComment(@PathVariable("commentID") int commentID, @RequestBody Comment comment) {
        Comment commentUpdate = commentService.findByID(commentID);
        commentUpdate.setCommentContent(comment.getCommentContent());
        commentUpdate.setCommentDate(java.time.LocalDate.now());
        return commentService.saveOrUpdate(commentUpdate);
    }
}
