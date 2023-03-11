package ProjectBlogOJT.payload.request;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {
    private String userName;
    private String password;
    private String email;
    private String fullName;
    private boolean userStatus;
    private Set<String> listRoles;


}
