package com.carapp.apigateway.auth;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String email;

    private Role role;

}
