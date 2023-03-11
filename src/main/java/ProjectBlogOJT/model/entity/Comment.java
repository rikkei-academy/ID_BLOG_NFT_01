package ProjectBlogOJT.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentID")
    private int commentID;

    @Column(name = "commentDate")
    private LocalDate commentDate;

    @Column(name = "commentContent")
    private String commentContent;

    @Column(name = "commentStatus")
    private boolean commentStatus;

    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "blogID")
    private Blog blog;
}
