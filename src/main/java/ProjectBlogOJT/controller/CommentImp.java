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
    public Product delete(@PathVariable("commentID") int commentID) {
        Comment comment = commentService.findByID(commentID);
        if (comment.isProductStatus() == true) {
            product.setProductStatus(false);
        } else {
            product.setProductStatus(true);
        }
        return productSevice.saveOrUpdate(product);
    }
}
