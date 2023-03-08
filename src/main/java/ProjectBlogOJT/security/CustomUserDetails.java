package ProjectBlogOJT.security;

import ProjectBlogOJT.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private int userID;
    private String userName;
    @JsonIgnore
    private String userPassword;
    private String userEmail;
    private String userDescription;
    private String userAvatar;
    private boolean userStatus;
    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public static CustomUserDetails mapUserToUserDetail(User user){
        List<GrantedAuthority> listAuthorities = user.getListRoles().stream()
                .map(roles -> new SimpleGrantedAuthority(roles.getRoleName().name())).collect(Collectors.toList());
        return new CustomUserDetails(
                user.getUserID(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserAvatar(),
                user.getUserDescription(),
                user.getUserPassword(),
                user.isUserStatus(),
                listAuthorities
        );
    }


}
