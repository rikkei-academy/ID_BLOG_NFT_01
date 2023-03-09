package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BlogService {
    List<Blog> findAll();

    Blog findByID(int blogID);

    Blog saveOrUpdate(Blog blog);

    void delete(int blogID);

    List<Blog> searchByTitle(String blogTitle);
}
