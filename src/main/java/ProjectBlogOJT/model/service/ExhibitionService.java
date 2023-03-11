package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface ExhibitionService {
    List<Exhibition> findAll();
    Exhibition findByID(int exhibitionID);
    Exhibition save(Exhibition exhibition);
    Exhibition saveOrUpdate(Exhibition exhibition);
    void delete(int exhibitionID);
    Page<Exhibition> sortBetween(LocalDate from, LocalDate to, Pageable pageable);

}
