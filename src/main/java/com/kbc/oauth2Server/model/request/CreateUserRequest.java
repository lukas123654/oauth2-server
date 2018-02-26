package com.kbc.oauth2Server.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateUserRequest {

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    private String firstName;

    private String lastName;

}
