package com.carapp.authentication;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="username")
    @NonNull
    private String username;

    @Column(name="password")
    @NonNull
    private String password;

    @NaturalId
    @Size(max = 40)
    @Email
    @NonNull
    @Column(name="email")
    private String email;

    @Column(name="role")
    @NonNull
    private Role role;

    public User(String username, String encode, String email, Role roleClient) {
    }
}
