package com.kbc.oauth2Server.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.kbc.oauth2Server.model.domain.City;
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
public class CitiesResponse {

    @NotNull
    @Valid
    private List<City> cities;
}
