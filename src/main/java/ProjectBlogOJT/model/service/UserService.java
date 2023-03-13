package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findByEmail(String email);

    User findByID(int userID);

    User saveOrUpdate(User user);

    void delete(int userID);

    List<User> searchByName(String userName);

    User findByUserName(String userName);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    List<User> listFilter(Integer option);

    List<User> sortByName(String directionName);

    Page<User> getPagging(Pageable pageable);

    List<User> findByFullName(String fullName);

    int followingQuantity(int followingUserID);
}
