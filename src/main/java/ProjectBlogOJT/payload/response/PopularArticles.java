package ProjectBlogOJT.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PopularArticles {
    private int blogID;
    private String blogTitle;
    private String blogContent;
    private String blogImage;
    private LocalDate blogCreateDate;
    private boolean blogStatus;
    private int like;

}
