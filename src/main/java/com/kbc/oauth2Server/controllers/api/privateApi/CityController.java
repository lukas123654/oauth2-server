package com.kbc.oauth2Server.controllers.api.privateApi;

import com.kbc.oauth2Server.controllers.api.PrivateApiController;
import com.kbc.oauth2Server.model.domain.User;
import com.kbc.oauth2Server.model.request.CreateCityRequest;
import com.kbc.oauth2Server.model.response.CitiesResponse;
import com.kbc.oauth2Server.model.response.CityResponse;
import com.kbc.oauth2Server.services.city.CityService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;

import static com.kbc.oauth2Server.controllers.api.PrivateApiController.CITY_URL;

@RestController
@RequestMapping(CITY_URL)
@PreAuthorize("hasAuthority('ADMIN_USER') or hasAuthority('STANDARD_USER')")
public class CityController extends PrivateApiController {

    private static final Logger logger = LoggerFactory.getLogger(CityController.class);

    @Autowired
    private CityService cityService;

    @ApiOperation(value = "Create new city", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCity(@Valid @RequestBody CreateCityRequest createCityRequest) {
        cityService.createNewCity(createCityRequest.getName(), createCityRequest.getState());
    }

    @ApiOperation(value = "Get all cities", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/all")
    public CitiesResponse findAllCities() {
        return new CitiesResponse(cityService.findAllCities());
    }

    @ApiOperation(value = "Find city by ID", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/{id}")
    public CityResponse findCityById(@PathVariable("id") Long id) {
        return new CityResponse(cityService.findCityById(id));
    }

    @ApiOperation(value = "Find city by (unique) name", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/name/{name}")
    public CityResponse findCityByName(@PathVariable("name") String name) {
        return new CityResponse(cityService.findCityByName(name));
    }

    @ApiOperation(value = "Find city containing name (full text search)", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/search/{name}")
    public CitiesResponse findCityContainingName(@PathVariable("name") String name) {
        return new CitiesResponse(cityService.findCityContainsName(name));
    }

    @ApiOperation(value = "Delete city by ID", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @DeleteMapping("/{id}")
    public void deleteCityById(@PathVariable("id") Long id, @ApiIgnore @ModelAttribute User user) {
        logger.info("user {} deleted city with id {}", user.getUsername(), id);
        cityService.deleteCityById(id);
    }

    @ApiOperation(value = "Delete city by (unique) name", tags = CITY_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @DeleteMapping("/name/{name}")
    public void deleteCityByName(@PathVariable("name") String name, @ApiIgnore @ModelAttribute User user) {
        logger.info("user {} deleted city with name {}", user.getUsername(), name);
        cityService.deleteCityByName(name);
    }
}
