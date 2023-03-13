package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes, Integer> {
    List<Likes> findLikesByLikeStatus(Boolean status);
    @Query(value = "select count(l.blogID) from Likes l where l.blogID = :blogID ",nativeQuery = true)
    int likeQuantity(@Param("blogID") int blogID);

}
