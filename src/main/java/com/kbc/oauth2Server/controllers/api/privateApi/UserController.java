package com.kbc.oauth2Server.controllers.api.privateApi;

import com.kbc.oauth2Server.controllers.api.PrivateApiController;
import com.kbc.oauth2Server.integration.oauth2.model.domain.User;
import com.kbc.oauth2Server.integration.oauth2.model.request.CreateUserRequest;
import com.kbc.oauth2Server.integration.oauth2.model.response.UserResponse;
import com.kbc.oauth2Server.integration.oauth2.model.response.UsersResponse;
import com.kbc.oauth2Server.services.user.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import static com.kbc.oauth2Server.controllers.api.PrivateApiController.USER_URL;

@RestController
@RequestMapping(USER_URL)
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class UserController extends PrivateApiController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "Create new user", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @PutMapping
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        userService.createNewUser(createUserRequest.getUsername(), createUserRequest.getPassword(),
                createUserRequest.getEmail(), createUserRequest.getFirstName(), createUserRequest.getLastName());
    }

    @ApiOperation(value = "Promote existing user to admin", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @PostMapping("/promote/{id}")
    public void promoteUser(@PathVariable("id") Long id) {
        userService.promoteUserToAdmin(id);
    }

    @ApiOperation(value = "Promote existing user to admin", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @PostMapping("/promote/name/{name}")
    public void promoteUser(@PathVariable("name") String name) {
        userService.promoteUserToAdmin(name);
    }

    @ApiOperation(value = "Get all users", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/all")
    public UsersResponse findAllUsers() {
        return new UsersResponse(userService.findAllUsers());
    }

    @ApiOperation(value = "Get user by ID", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/{id}")
    public UserResponse findUserById(@PathVariable("id") Long id) {
        return new UserResponse(userService.findUserById(id));
    }

    @ApiOperation(value = "Get user by (unique) name", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/name/{name}")
    public UserResponse findUserByName(@PathVariable("name") String name) {
        return new UserResponse(userService.findUserByUsername(name));
    }

    @ApiOperation(value = "Find user containing name (full text search)", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @GetMapping("/search/{name}")
    public UsersResponse findUserContainingUsername(@PathVariable("name") String name) {
        return new UsersResponse(userService.findUserContainsUsername(name));
    }

    @ApiOperation(value = "Delete user by (unique) name", tags = USER_TAG)
    @ApiImplicitParams({
            @ApiImplicitParam(name = authHeader, value = authHeaderValue, paramType = header)
    })
    @DeleteMapping("/name/{name}")
    public void deleteUserById(@PathVariable("name") String name, @ApiIgnore @ModelAttribute User user) {
        logger.info("user {} deleted city with name {}", user.getUsername(), name);
        userService.deleteUserByUsername(name);
    }

}
