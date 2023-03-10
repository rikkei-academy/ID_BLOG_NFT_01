package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

public interface CommentService {
    List<Comment> findAll();
    Comment findByID(int commentID);
    Comment save(Comment comment);
    Comment saveOrUpdate(Comment comment);
    void delete(int commentID);
    Page<Comment> sortBetween(LocalDate from, LocalDate to, Pageable pageable);
}
