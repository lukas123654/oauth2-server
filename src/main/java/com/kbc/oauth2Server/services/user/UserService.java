package com.kbc.oauth2Server.services.user;

import com.kbc.oauth2Server.model.domain.User;

import java.util.List;

public interface UserService {

    User createNewUser(String username, String password, String email, String firstName, String lastName);

    User promoteUserToAdmin(Long id);

    User promoteUserToAdmin(String username);

    List<User> findAllUsers();

    User findUserById(Long id);

    User findUserByUsername(String username);

    List<User> findUserContainsUsername(String username);

    Long deleteUserByUsername(String username);

}
