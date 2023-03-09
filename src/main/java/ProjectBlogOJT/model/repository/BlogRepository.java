package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findByBlogTitleContaining(String blogTitle);
}
