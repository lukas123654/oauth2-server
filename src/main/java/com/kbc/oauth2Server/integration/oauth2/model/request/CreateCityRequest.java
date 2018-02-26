package com.kbc.oauth2Server.integration.oauth2.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateCityRequest {

    @NotNull
    private String name;

    @NotNull
    private String state;
}
