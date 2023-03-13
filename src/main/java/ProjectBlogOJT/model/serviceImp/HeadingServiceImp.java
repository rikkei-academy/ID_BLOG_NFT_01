package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Heading;
import ProjectBlogOJT.model.repository.HeadingRepository;
import ProjectBlogOJT.model.service.HeadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeadingServiceImp implements HeadingService {
    @Autowired
    HeadingRepository headingRepository;
    @Override
    public Heading getHeading(int headingID) {
        return headingRepository.findById(headingID).get();
    }

    @Override
    public Heading createHeading(Heading heading) {
        return headingRepository.save(heading);
    }

    @Override
    public Heading updateHeading(Heading heading) {
        return headingRepository.save(heading);
    }

    @Override
    public Heading findByID(int headingID) {
        return headingRepository.findById(headingID).get();
    }

}
