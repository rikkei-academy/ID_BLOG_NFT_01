package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Following;
import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface FollowingService {
    List<Following> findAll();
    Following findByID(int followID);
    Following save(Following followID);
    Following saveOrUpdate(Following followID);
    void delete(int followID);
    Page<Following> sortBetween(LocalDate from, LocalDate to, Pageable pageable);

}
