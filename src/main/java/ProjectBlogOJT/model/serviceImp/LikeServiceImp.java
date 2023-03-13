package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.Likes;
import ProjectBlogOJT.model.repository.LikeRepository;
import ProjectBlogOJT.model.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImp implements LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Override
    public List<Likes> findAll() {
        return likeRepository.findLikesByLikeStatus(true) ;
    }

    @Override
    public Likes findByID(int likeID) {
        return likeRepository.findById(likeID).get();
    }

    @Override
    public Likes save(Likes likes) {
        return likeRepository.save(likes);
    }
    @Override
    public void delete(int likeID) {
        likeRepository.deleteById(likeID);
    }

    @Override
    public int countLike(int blogID) {
        return likeRepository.likeQuantity(blogID);
    }
}
