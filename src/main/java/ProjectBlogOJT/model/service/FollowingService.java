package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Following;
import ProjectBlogOJT.model.entity.Product;

import java.util.List;

public interface FollowingService {
    List<Following> findAll();
    Following findByID(int followID);
    Following save(Following followID);
    Following saveOrUpdate(Following followID);
    void delete(int followID);
}
