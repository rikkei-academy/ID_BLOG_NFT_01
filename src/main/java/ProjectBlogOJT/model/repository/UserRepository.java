package ProjectBlogOJT.model.repository;

import ProjectBlogOJT.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
    List<User> findUserByUserNameContaining(String userName);
    boolean existsByUserName(String userName);
    boolean existsByUserEmail(String email);
    User findByUserEmail(String email);

    List<User> findByUserFullNameContaining(String fullName);

    @Query(value = "select count(f.followingUserID) from Following f where f.followingUserID = :followingUserID ",nativeQuery = true)
    int followingQuantity(@Param("followingUserID") int followingUserID);

}
