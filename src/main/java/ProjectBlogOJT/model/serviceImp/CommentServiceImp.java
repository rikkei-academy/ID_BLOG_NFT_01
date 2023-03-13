package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.repository.CommentRepository;
import ProjectBlogOJT.model.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Override
    public List<Comment> findAll() {
        return commentRepository.findCommentByCommentStatus(true);
    }

    @Override
    public Comment findByID(int commentID) {
        return commentRepository.findById(commentID).get();
    }

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment saveOrUpdate(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public void delete(int commentID) {
        commentRepository.deleteById(commentID);
    }

    @Override
    public Page<Comment> sortBetween(LocalDate from, LocalDate to, Pageable pageable) {
        return commentRepository.findCommentByCommentDateBetween(from,to,pageable);
    }
}
