package ProjectBlogOJT.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "User")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private int userID;
    @Column(name = "userName")
    private String userName;
    @Column(name = "userPassword")
    private String userPassword;
    @Column(name = "userEmail")
    private String userEmail;
    @Column(name = "userDescription")
    private String userDescription;
    @Column(name = "userAvatar")
    private String userAvatar;
    @Column(name = "userStatus")
    private boolean userStatus;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "User_Role", joinColumns = @JoinColumn(name = "userID"),
    inverseJoinColumns = @JoinColumn(name="RoleId"))
    private Set<Roles> listRoles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "Likes", joinColumns = @JoinColumn(name = "userID"),
            inverseJoinColumns = @JoinColumn(name="blogID"))
    private List<Blog> listTag = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<History> listHistory;


}
