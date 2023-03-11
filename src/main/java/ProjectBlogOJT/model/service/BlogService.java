package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
@Service
public interface BlogService {
    List<Blog> findAll();

    Blog findByID(int blogID);

    Blog saveOrUpdate(Blog blog);

    void delete(int blogID);

    List<Blog> searchByTitle(String blogTitle);
    List<Blog> sortByCreatedDate(String direction);
    Page<Blog> findByBlogCreateDateBetweenAndSort(LocalDate from, LocalDate to, Pageable pageable);
    List<Blog> findByUserID(int userID);
}
