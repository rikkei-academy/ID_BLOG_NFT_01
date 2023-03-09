package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Product;
import ProjectBlogOJT.model.entity.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAll();
    Tag findByID(int tagID);
    Tag save(Tag tag);
    Tag saveOrUpdate(Tag tag);
    void delete(int tagID);
}
