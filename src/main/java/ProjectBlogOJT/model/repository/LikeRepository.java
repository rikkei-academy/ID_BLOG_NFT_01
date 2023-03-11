package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {
    List<Likes> findLikesByLikeStatus(Boolean status);

}
