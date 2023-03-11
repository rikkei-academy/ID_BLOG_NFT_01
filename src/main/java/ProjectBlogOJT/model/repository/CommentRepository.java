package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Page<Comment> findCommentByCommentDateBetween(LocalDate from, LocalDate to, Pageable pageable);
    List<Comment> findCommentByCommentStatus(Boolean status);

}
