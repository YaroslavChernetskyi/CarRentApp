package com.carapp.apigateway.auth;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@FeignClient(value = "authentication")
public interface AuthClient {

    @PostMapping("api/auth/login")
    public ResponseEntity<?> authenticateUser(@RequestParam(value = "username", required = true) String username,
                                              @RequestParam(value = "password", required = true) String password);


    @PostMapping("api/auth/register")
    public ResponseEntity<?> registerUser(@RequestParam(value = "username", required = true) String username,
                                          @RequestParam(value = "password", required = true) String password,
                                          @RequestParam(value = "email", required = true) String email);

    @GetMapping("api/auth/current")
    public List<Object> getCurrentUser(@RequestHeader(value = "Authorization") String token);

    @GetMapping("api/auth/currentId")
    public Long getCurrentUserId(@RequestHeader(value = "Authorization") String token);

    @GetMapping("api/auth/user/{id}")
    public User getUsernameById(@PathVariable Long id);

    @RequestMapping(path="api/auth/isAdmin", method = RequestMethod.GET)
    public Boolean isAdmin();

    @RequestMapping(path="api/auth/isClient", method = RequestMethod.GET)
    public Boolean isClient();
}
