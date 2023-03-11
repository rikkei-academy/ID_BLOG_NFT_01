package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Likes;
import ProjectBlogOJT.model.entity.Product;

import java.util.List;

public interface LikeService {
    List<Likes> findAll();
    Likes findByID(int likeID);
    Likes save(Likes likes);
    void delete(int likeID);
}
