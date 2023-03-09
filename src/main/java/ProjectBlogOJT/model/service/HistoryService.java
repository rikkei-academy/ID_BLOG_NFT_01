package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.entity.Product;

import java.util.List;

public interface HistoryService {
    List<History> findAll();
    History findByID(int historyID);
    History save(History history);
    History saveOrUpdate(History history);
    void delete(int historyID);
}
