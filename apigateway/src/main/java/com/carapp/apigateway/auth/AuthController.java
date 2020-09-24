package com.carapp.apigateway.auth;

import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    AuthClient authClient;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam(value = "username", required = true) String username,
                                              @RequestParam(value = "password", required = true) String password) {

       return authClient.authenticateUser(username, password);
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam(value = "username", required = true) String username,
                                          @RequestParam(value = "password", required = true) String password,
                                          @RequestParam(value = "email", required = true) String email) {
        return authClient.registerUser(username, password, email);
    }

    @GetMapping("/current")
    public List<Object> getCurrentUser(@RequestHeader(value = "Authorization") String token) {
        return authClient.getCurrentUser(token);
    }

    @GetMapping("/currentId")
    public Long getCurrentUserId(@RequestHeader(value = "Authorization") String token) {
        return authClient.getCurrentUserId(token);
    }

    @GetMapping("/user/{id}")
    public User getUsernameById(@PathVariable Long id){
        return authClient.getUsernameById(id);
    }
}