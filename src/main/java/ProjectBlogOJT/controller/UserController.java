package ProjectBlogOJT.controller;

import ProjectBlogOJT.jwt.JwtTokenProvider;
import ProjectBlogOJT.model.entity.ERole;
import ProjectBlogOJT.model.entity.Roles;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.RoleService;
import ProjectBlogOJT.model.service.UserSevice;
import ProjectBlogOJT.payload.request.LoginRequest;
import ProjectBlogOJT.payload.request.SignupRequest;
import ProjectBlogOJT.payload.response.JwtResponse;
import ProjectBlogOJT.payload.response.MessageResponse;
import ProjectBlogOJT.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/auth")
public class UserController {
    @Autowired
    private UserSevice userSevice;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider tokenProvider;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder encoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest){
        if(userSevice.existsByUserName(signupRequest.getUserName())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Usermame is already"));
        }
        if (userSevice.existsByEmail(signupRequest.getEmail())){
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already"));

        }
        User users = new User();
        users.setUserName(signupRequest.getUserName());
        users.setUserPassword(encoder.encode(signupRequest.getPassword()));
        users.setUserEmail(signupRequest.getEmail());
        users.setUserStatus(true);
        Set<String> strRoles = signupRequest.getListRoles();
        Set<Roles> listRoles = new HashSet<>();
        if(strRoles == null){
            Roles userRole = roleService.findByRoleName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: Role is not found"));
            listRoles.add(userRole);
        }else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Roles adminRole = roleService.findByRoleName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(adminRole);
                    case "moderator":
                        Roles modRole = roleService.findByRoleName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(modRole);
                    case "user":
                        Roles userRole = roleService.findByRoleName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found"));
                        listRoles.add(userRole);
                }
            });
        }
        users.setListRoles(listRoles);
        userSevice.saveOrUpdate(users);
        return ResponseEntity.ok(users);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        User users = userSevice.findByEmail(customUserDetails.getUserEmail());
        if(!customUserDetails.isUserStatus()){
            return ResponseEntity.ok("Your account have been block !");
        } else {
            String jwt = tokenProvider.generateToken(customUserDetails);
            List<String> listRoles = customUserDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, users.getUserID(), users.getUserName(), users.getUserEmail(), listRoles));
        }
    }


}
