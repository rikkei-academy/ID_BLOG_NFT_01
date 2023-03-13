package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.History;
import ProjectBlogOJT.model.repository.HistoryRepository;
import ProjectBlogOJT.model.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public List<History> sortByDateCreate(String direction) {
        if (direction.equals("asc")) {
            return historyRepository.findAll(Sort.by("historyDataTime"));
        } else {
            return historyRepository.findAll(Sort.by("historyDataTime").descending());
        }    }

    @Override
    public Page<History> sortBetween(LocalDate from, LocalDate to, Pageable pageable) {
        return historyRepository.findHistoriesByHistoryDataTime(from,to,pageable);
    }
}
