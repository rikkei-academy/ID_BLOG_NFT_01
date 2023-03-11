package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findByBlogTitleContaining(String blogTitle);
    Page<Blog> findByBlogCreateDateBetween(LocalDate from, LocalDate to, Pageable pageable);
    List<Blog> findByUser_UserID(int userID);
}
