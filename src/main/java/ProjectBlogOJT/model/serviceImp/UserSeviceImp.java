package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.repository.UserRepository;
import ProjectBlogOJT.model.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.List;

@Service
@Transactional(rollbackOn = SQLException.class)
public class UserSeviceImp implements UserSevice {
    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByUserEmail(email);
    }

    @Override
    public User findByID(int userID) {
        return userRepository.findById(userID).get();
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(int userID) {
       userRepository.deleteById(userID);
    }

    @Override
    public List<User> searchByName(String userName) {
        return null;
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public boolean existsByUserName(String userName) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
