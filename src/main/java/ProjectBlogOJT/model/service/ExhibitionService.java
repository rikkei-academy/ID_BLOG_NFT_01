package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.entity.Product;

import java.util.List;

public interface ExhibitionService {
    List<Exhibition> findAll();
    Exhibition findByID(int exhibitionID);
    Exhibition save(Exhibition exhibition);
    Exhibition saveOrUpdate(Exhibition exhibition);
    void delete(int exhibitionID);
}
