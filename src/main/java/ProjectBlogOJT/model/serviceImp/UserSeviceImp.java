package ProjectBlogOJT.model.serviceImp;

import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.repository.UserRepository;
import ProjectBlogOJT.model.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return userRepository.findUserByUserNameContaining(userName);
    }

    @Override
    public User findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public boolean existsByUserName(String userName) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }

    @Override
    public List<User> listFilter(Integer option) {
        List<User> userList = userRepository.findAll();
        List<User> listFilter = new ArrayList<>();
        for (User users : userList ) {
            if (users.getListRoles().size()==option){
                listFilter.add(users);
            }
        }
        return listFilter;
    }

    @Override
    public List<User> sortByName(String directionName) {
        if(directionName.equals("asc")){
            return userRepository.findAll(Sort.by("userName"));
        }else {
            return  userRepository.findAll(Sort.by("userName").descending());
        }
    }

    @Override
    public Page<User> getPagging(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

}
