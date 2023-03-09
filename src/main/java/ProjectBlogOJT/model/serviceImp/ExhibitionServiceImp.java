package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Exhibition;
import ProjectBlogOJT.model.repository.ExhibitionRepository;
import ProjectBlogOJT.model.service.ExhibitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
