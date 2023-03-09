package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Following;
import ProjectBlogOJT.model.repository.FollowingRepository;
import ProjectBlogOJT.model.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FollowingServiceImp implements FollowingService {
    @Autowired
    FollowingRepository followingRepository;


    @Override
    public List<Following> findAll() {
        return followingRepository.findAll();
    }

    @Override
    public Following findByID(int followID) {
        return followingRepository.findById(followID).get();
    }

    @Override
    public Following save(Following follow) {
        return followingRepository.save(follow);
    }

    @Override
    public Following saveOrUpdate(Following follow) {
        return followingRepository.save(follow);
    }

    @Override
    public void delete(int followID) {
        followingRepository.deleteById(followID);
    }
}
