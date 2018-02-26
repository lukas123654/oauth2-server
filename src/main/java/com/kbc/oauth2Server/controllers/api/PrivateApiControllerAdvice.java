package com.kbc.oauth2Server.controllers.api;

import com.kbc.oauth2Server.integration.oauth2.model.backend.CurrentlyLoggedUser;
import com.kbc.oauth2Server.integration.oauth2.model.domain.User;
import com.kbc.oauth2Server.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(basePackages = "com.kbc.oauth2Server.controllers.api.privateApi")
public class PrivateApiControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(PrivateApiControllerAdvice.class);

    @Autowired
    private UserService userService;

    @ModelAttribute
    public User mapUser(@CurrentlyLoggedUser String username) {
        logger.info("setting @ModelAttribute for user {}", username);
        return userService.findUserByUsername(username);
    }

}
