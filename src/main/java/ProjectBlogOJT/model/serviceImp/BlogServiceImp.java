package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Blog;
import ProjectBlogOJT.model.repository.BlogRepository;
import ProjectBlogOJT.model.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BlogServiceImp implements BlogService {
    @Autowired
    BlogRepository blogRepository;

    @Override
    public List<Blog> findAll() {

        return blogRepository.findAll();
    }

    @Override
    public Blog findByID(int blogID) {
        return blogRepository.findById(blogID).get();
    }

    @Override
    public Blog saveOrUpdate(Blog blog) {
        return blogRepository.save(blog);
    }

    @Override
    public void delete(int blogID) {
        blogRepository.deleteById(blogID);
    }

    @Override
    public List<Blog> searchByTitle(String blogTitle) {
        return blogRepository.findByBlogTitleContaining(blogTitle);
    }

    @Override
    public List<Blog> sortByCreatedDate(String direction) {

        if(direction.equals("asc")){
            return blogRepository.findAll(Sort.by("blogCreateDate").ascending());
        }else {
            return blogRepository.findAll(Sort.by("blogCreateDate").descending());
        }
    }


}
