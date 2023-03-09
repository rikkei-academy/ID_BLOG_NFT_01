package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String email);
    User findByUserEmail(String email);
    List<User> findUserByUserNameContaining(String userName);

}
