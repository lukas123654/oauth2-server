package com.kbc.oauth2Server.services.user;

import com.kbc.oauth2Server.exceptions.DeleteAdminException;
import com.kbc.oauth2Server.exceptions.JpaCreateException;
import com.kbc.oauth2Server.exceptions.JpaFindException;
import com.kbc.oauth2Server.model.domain.Role;
import com.kbc.oauth2Server.model.domain.User;
import com.kbc.oauth2Server.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.Arrays.asList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createNewUser(String username, String password, String email, String firstName, String lastName) {
        User user;
        try {
            Role role = new Role("STANDARD_USER", "Standard User - Has no admin rights");
            user = userRepository.saveAndFlush(new User(username, passwordEncoder.encode(password), email,
                    firstName, lastName, asList(role)));
        }
        catch (DataIntegrityViolationException e) {
            throw new JpaCreateException("User with given username already exists");
        }

        return user;
    }

    @Override
    @Transactional
    public User promoteUserToAdmin(Long id) {
        User user = userRepository.findOne(id);
        if (user == null) {
            throw new JpaFindException("User with id " + id + " doesn't exist");
        }
        return addAdminRole(user);
    }

    @Override
    @Transactional
    public User promoteUserToAdmin(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new JpaFindException("User " + username + " doesn't exist");
        }
        return addAdminRole(user);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findUserContainsUsername(String username) {
        return userRepository.findByUsernameContainingIgnoreCase(username);
    }

    @Override
    @Transactional
    public Long deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new JpaFindException("User with username " + username + " doesn't exist");
        }
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ADMIN_USER"))) {
            throw new DeleteAdminException("User with role ADMIN_USER cannot be deleted");
        }
        return userRepository.deleteByUsername(username);
    }

    private User addAdminRole(User user) {
        if (user.getRoles().stream().anyMatch(role -> role.getRoleName().equals("ADMIN_USER"))) {
            return user;
        }
        Role role = new Role("ADMIN_USER", "Admin User - Has permission to perform admin tasks");
        user.getRoles().add(role);
        return userRepository.saveAndFlush(user);
    }
}
