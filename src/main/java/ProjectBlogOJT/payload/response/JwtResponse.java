package ProjectBlogOJT.payload.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

public class JwtResponse {
    private int userId;
    private String token;
    private String type = "Bearer";
    private String userName;
    private String userAvatar;
    private String email;
    private List<String> listRoles;

    public JwtResponse(int userId, String token, String type, String userName, String userAvatar, String email, List<String> listRoles) {
        this.userId = userId;
        this.token = token;
        this.type = type;
        this.userName = userName;
        this.userAvatar = userAvatar;
        this.email = email;
        this.listRoles = listRoles;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserID(int userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public List<String> getListRoles() {
        return listRoles;
    }

    public void setListRoles(List<String> listRoles) {
        this.listRoles = listRoles;
    }
}
