package ProjectBlogOJT.controller;

import ProjectBlogOJT.jwt.JwtTokenProvider;
import ProjectBlogOJT.model.entity.ERole;
import ProjectBlogOJT.model.entity.Roles;
import ProjectBlogOJT.model.entity.User;
import ProjectBlogOJT.model.service.RoleService;
import ProjectBlogOJT.model.service.UserSevice;
import ProjectBlogOJT.payload.request.ChangePass;
import ProjectBlogOJT.payload.request.LoginRequest;
import ProjectBlogOJT.payload.request.SignupRequest;
import ProjectBlogOJT.payload.response.JwtResponse;
import ProjectBlogOJT.payload.response.MessageResponse;
import ProjectBlogOJT.security.CustomUserDetails;
import ProjectBlogOJT.sendEmail.ProvideSendEmail;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private ProvideSendEmail provideSendEmail;

    @GetMapping("/getToken")
    public ResponseEntity<?> sendEmail(@RequestParam("email") String email) {
        try {
            String jwt = tokenProvider.generateTokenEmail(email);
            provideSendEmail.sendSimpleMessage(email, "Token", jwt);
            return ResponseEntity.ok("Send email successfully");
        } catch (Exception e) {
            return ResponseEntity.ok("Failed");
        }
    }

    @PostMapping("/resetPass")
    public User resetPass(@RequestParam("token") String token, @RequestBody String newPass) {
        String userName = tokenProvider.getUserNameFromJwt(token);
        User user = userSevice.findByUserName(userName);
        user.setUserPassword(encoder.encode(newPass));

        return userSevice.saveOrUpdate(user);
    }
    @PutMapping("/changePass")
    public ResponseEntity<?> changePassword(@RequestBody ChangePass changePass) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User users = userSevice.findByID(userDetails.getUserId());
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        boolean passChecker = bc.matches(changePass.getOldPassword(), users.getUserPassword());
        if (passChecker) {
            boolean checkDuplicate = bc.matches(changePass.getPassword(), users.getUserPassword());
            if (checkDuplicate) {
                return ResponseEntity.ok(new MessageResponse("The new password must be different from the old password !"));
            } else {
                users.setUserPassword(encoder.encode(changePass.getPassword()));
                userSevice.saveOrUpdate(users);
                return ResponseEntity.ok(new MessageResponse("Change password successfully !"));
            }
        } else {
            return ResponseEntity.ok(new MessageResponse("Password does not match ! Change password fail"));
        }
    }
    @GetMapping("/logOut")
    public ResponseEntity<?> logOut(HttpServletRequest request){
        String authorizationHeader = request.getHeader("Authorization");


        // Clear the authentication from server-side (in this case, Spring Security)
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("You have been logged out.");

    }



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
        User users = userSevice.findByEmail(customUserDetails.getEmail());
        if(!customUserDetails.isUserStatus()){
            return ResponseEntity.ok("Your account have been block !");
        } else {
            String jwt = tokenProvider.generateToken(customUserDetails);
            List<String> listRoles = customUserDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority()).collect(Collectors.toList());
            return ResponseEntity.ok(new JwtResponse(jwt, users.getUserID(), users.getUserName(), users.getUserEmail(), listRoles));
        }
    }
    @PostMapping("/block/{userID}")
    public ResponseEntity<?> blockUser(@PathVariable("userID") int userID) {
        User userBlock = userSevice.findByID(userID);
        userBlock.setUserStatus(false);
        userSevice.saveOrUpdate(userBlock);
        return ResponseEntity.ok("Block Successfully !");
    }
    @GetMapping("/searchUser/{userName}")
    public List<User> listSearch(@PathVariable("userName") String userName){
        List<User> listSearch = userSevice.searchByName(userName);
        return listSearch ;
    }


}
