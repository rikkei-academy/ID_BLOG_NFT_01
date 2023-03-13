package ProjectBlogOJT.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PopularArtist {
    int userID;
    String userFullName;
    String userTagName;
    String userAvatar;
    int followQuantity;
}
