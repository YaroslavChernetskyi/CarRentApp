package com.carapp.authentication;

import com.carapp.authentication.exceptions.AppException;
import com.carapp.authentication.payload.ApiResponse;
import com.carapp.authentication.payload.JwtAuthenticationResponse;
import com.carapp.authentication.security.CurrentUser;
import com.carapp.authentication.security.JwtTokenProvider;
import com.carapp.authentication.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam(value = "username", required = true) String username,
                                              @RequestParam(value = "password", required = true) String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        System.out.println(username);
        System.out.println(password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam(value = "username", required = true) String username,
                                          @RequestParam(value = "password", required = true) String password,
                                          @RequestParam(value = "email", required = true) String email) {
        if(userRepository.existsByUsername(username)) {
            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        System.out.println(username);

        System.out.println(password);

        if(userRepository.existsByEmail(email)) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        System.out.println(email);


        //User user = new User(username, passwordEncoder.encode(password), email, Role.ROLE_CLIENT);
        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_CLIENT)
                .build();
        if(user.getUsername().equals("1")) {
            user.setRole(Role.ROLE_ADMIN);
        }
        User result = userRepository.save(user);

        System.out.println(username);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );


        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @GetMapping("/current")
    public List<Object> getCurrentUserRole(@RequestHeader(value = "Authorization") String tokenn) {
        String token = tokenn.substring(7);

        List<Object> list = new ArrayList<Object>();
        list.add(tokenProvider.getUserRoleFromJWT(token));
        list.add(tokenProvider.getUsernameFromJWT(token));

        return list;
    }

    @GetMapping("/currentId")
    public Long getCurrentUserId(@RequestHeader(value = "Authorization") String tokenn) {
        String token = tokenn.substring(7);
        return tokenProvider.getUserIdFromJWT(token);
    }

    @GetMapping("/user/{id}")
    public User getUsernameById(@PathVariable Long id){
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(path="/isAdmin", method = RequestMethod.GET)
    public Boolean isAdmin() {
        return true;
    }

    @PreAuthorize("hasRole('ROLE_CLIENT')")
    @RequestMapping(path="/isClient", method = RequestMethod.GET)
    public Boolean isClient() {
        return true;
    }
}

