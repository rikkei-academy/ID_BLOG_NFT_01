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

    @Column(name = "followingUserID")
    private int followingUserID;

    @Column(name = "followStatus")
    private boolean followStatus;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;
}
