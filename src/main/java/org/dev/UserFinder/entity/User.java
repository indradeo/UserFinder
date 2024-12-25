package org.dev.UserFinder.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "* username is mandatory")
    private String username;
    @NotBlank(message = "* first name is mandatory")
    private String firstName;
    @NotBlank(message = "* last name is mandatory")
    private String lastName;
    @NotBlank(message = "* email is mandatory")
    private String email;
    @NotBlank(message = "* password is mandatory")
    private String password;
}
