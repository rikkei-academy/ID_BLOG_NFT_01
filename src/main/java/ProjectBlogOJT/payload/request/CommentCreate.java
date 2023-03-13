package ProjectBlogOJT.payload.request;

import ProjectBlogOJT.model.entity.Blog;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.hql.internal.ast.tree.IdentNode;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentCreate {
    private LocalDate commentDate;
    private String commentContent;
    private boolean commentStatus;
    private Integer blogID;

}
