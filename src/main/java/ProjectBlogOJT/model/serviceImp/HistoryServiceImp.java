package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.repository.HistoryRepository;
import ProjectBlogOJT.model.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class HistoryServiceImp implements HistoryService {
    @Autowired
    HistoryRepository historyRepository;


    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public History findByID(int historyID) {
        return historyRepository.findById(historyID).get();
    }

    @Override
    public History save(History history) {
        return historyRepository.save(history);
    }

    @Override
    public History saveOrUpdate(History history) {
        return historyRepository.save(history);
    }

    @Override
    public void delete(int historyID) {
        historyRepository.deleteById(historyID);
    }
}
