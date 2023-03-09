package ProjectBlogOJT.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Following")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Following {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "followID")
    private int followID;
    @ManyToOne
    @JoinColumn(name = "followerUserID")
    private User followerUser;
    @ManyToOne
    @JoinColumn(name = "followingUserID")
    private User followingUser;
    @Column(name = "followStatus")
    private int followStatus;

}
