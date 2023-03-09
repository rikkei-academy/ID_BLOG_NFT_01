package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Product;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findByID(int commentID);
    Comment save(Comment comment);
    Comment saveOrUpdate(Comment comment);
    void delete(int commentID);
}
