package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Integer> {
}
