package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "Blog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blogID")
    private int blogID;
    @Column(name = "blogTitle")
    private String blogTitle;
    @Column(name = "blogContent")
    private String blogContent;
    @Column(name = "blogImage")
    private String blogImage;
    @Column(name = "blogCreateDate")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date blogCreateDate;
    @ManyToOne
    @JsonIgnore

    @JoinColumn(name = "UserCreatedID")
    private User userCreated;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "UserAuthorizedID")
    private User userAuthorized;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name= "Blog_Tag", joinColumns = @JoinColumn(name = "blogID"),
            inverseJoinColumns = @JoinColumn(name="tagID"))
    private Set<Tag> listTag = new HashSet<>();
    @OneToMany(mappedBy = "blog")
    private List<Comment> commentList;

}
