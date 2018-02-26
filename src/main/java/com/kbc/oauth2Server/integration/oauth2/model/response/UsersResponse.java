package com.kbc.oauth2Server.integration.oauth2.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kbc.oauth2Server.integration.oauth2.model.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersResponse {

    @NotNull
    @Valid
    private List<User> users;
}