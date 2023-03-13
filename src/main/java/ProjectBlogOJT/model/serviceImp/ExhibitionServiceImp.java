package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Comment;
import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.repository.ExhibitionRepository;
import ProjectBlogOJT.model.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ExhibitionServiceImp implements ExhibitionService {
    @Autowired
    ExhibitionRepository exhibitionRepository;


    @Override
    public List<Exhibition> findAll() {
        return exhibitionRepository.findAll();
    }

    @Override
    public Exhibition findByID(int exhibitionID) {
        return exhibitionRepository.findById(exhibitionID).get();
    }

    @Override
    public Exhibition save(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    @Override
    public Exhibition saveOrUpdate(Exhibition exhibition) {
        return exhibitionRepository.save(exhibition);
    }

    @Override
    public void delete(int exhibitionID) {
        exhibitionRepository.deleteById(exhibitionID);
    }

    @Override
    public List<Exhibition> sortByDateCreate(String direction) {
        if (direction.equals("asc")) {
            return exhibitionRepository.findAll(Sort.by("exhibitionCreatedDate"));
        } else {
            return exhibitionRepository.findAll(Sort.by("exhibitionCreatedDate").descending());
        }
    }

    @Override
    public Page<Exhibition> sortBetween(LocalDate from, LocalDate to, Pageable pageable) {
        Page<Exhibition> exhibitionPage =  exhibitionRepository.findExhibitionByExhibitionCreatedDate(from,to,pageable);
        return exhibitionPage;
    }
}
