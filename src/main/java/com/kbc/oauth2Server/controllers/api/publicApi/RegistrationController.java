package com.kbc.oauth2Server.controllers.api.publicApi;

import com.kbc.oauth2Server.controllers.api.ApiController;
import com.kbc.oauth2Server.controllers.api.PublicApiController;
import com.kbc.oauth2Server.model.request.CreateUserRequest;
import com.kbc.oauth2Server.services.user.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.kbc.oauth2Server.controllers.api.PublicApiController.REGISTRATION_URL;

@RestController
@RequestMapping(REGISTRATION_URL)
public class RegistrationController extends PublicApiController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Register new user", tags = ApiController.REGISTER_TAG)
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        userService.createNewUser(createUserRequest.getUsername(), createUserRequest.getPassword(),
                createUserRequest.getEmail(), createUserRequest.getFirstName(), createUserRequest.getLastName());
    }

}
