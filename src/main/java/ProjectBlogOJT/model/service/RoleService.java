package ProjectBlogOJT.model.service;

import ProjectBlogOJT.model.entity.ERole;
import ProjectBlogOJT.model.entity.Roles;

import java.util.Optional;

public interface RoleService {
    Optional<Roles> findByRoleName(ERole roleName);

}
