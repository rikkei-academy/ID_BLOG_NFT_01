package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Heading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeadingRepository extends JpaRepository<Heading, Integer> {
}
