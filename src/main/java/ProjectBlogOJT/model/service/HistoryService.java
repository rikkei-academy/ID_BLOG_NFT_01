package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History findByID(int historyID);
    History save(History history);
    History saveOrUpdate(History history);
    void delete(int historyID);
    Page<History> sortBetween(LocalDate from, LocalDate to, Pageable pageable);

}
