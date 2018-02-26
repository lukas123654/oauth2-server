package com.kbc.oauth2Server.integration.oauth2.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kbc.oauth2Server.integration.oauth2.model.domain.City;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CityResponse {

    @NotNull
    @Valid
    private City city;
}
