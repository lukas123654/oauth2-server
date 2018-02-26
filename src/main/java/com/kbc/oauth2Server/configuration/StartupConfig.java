package com.kbc.oauth2Server.configuration;

import com.kbc.oauth2Server.model.domain.City;
import com.kbc.oauth2Server.model.domain.Role;
import com.kbc.oauth2Server.model.domain.User;
import com.kbc.oauth2Server.model.repository.CityRepository;
import com.kbc.oauth2Server.model.repository.RoleRepository;
import com.kbc.oauth2Server.model.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import static java.util.Arrays.asList;

@Configuration
@Profile("!test")
public class StartupConfig {

    private final Logger logger = LoggerFactory.getLogger(StartupConfig.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner demo() {
        return (args) -> {
            userRepository.saveAndFlush(new User(
                    "userTest", passwordEncoder.encode("test"), "userTest@kbc.com", "User", "Test",
                    asList(
                            new Role("STANDARD_USER", "Standard User - Has no admin rights"))));

            userRepository.saveAndFlush(new User(
                    "userAdmin", passwordEncoder.encode("admin"), "userAdmin@kbc.com", "User", "Admin",
                    asList(
                            new Role("STANDARD_USER", "Standard User - Has no admin rights"),
                            new Role("ADMIN_USER", "Admin User - Has permission to perform admin tasks"))));

            cityRepository.saveAndFlush(new City("Bratislava", "Slovakia"));
            cityRepository.saveAndFlush(new City("Berlin", "Germany"));
            cityRepository.saveAndFlush(new City("Paris", "France"));
            cityRepository.saveAndFlush(new City("Brussels", "Belgium"));
            cityRepository.saveAndFlush(new City("Madrid", "Spain"));

            logger.info("user repository count: {}", userRepository.count());
            logger.info("city repository count: {}", cityRepository.count());
            logger.info("role repository count: {}", roleRepository.count());

            logger.info("users: {}", userRepository.findAll().toString());
            logger.info("cities: {}", cityRepository.findAll().toString());
            logger.info("roles: {}", roleRepository.findAll().toString());
        };
    }

}
