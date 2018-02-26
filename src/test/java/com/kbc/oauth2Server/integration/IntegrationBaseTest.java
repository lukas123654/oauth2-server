package com.kbc.oauth2Server.integration;

import com.kbc.oauth2Server.integration.model.Oauth2Constants;
import com.kbc.oauth2Server.model.domain.City;
import com.kbc.oauth2Server.model.domain.Role;
import com.kbc.oauth2Server.model.domain.User;
import com.kbc.oauth2Server.model.repository.CityRepository;
import com.kbc.oauth2Server.model.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Arrays.asList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ActiveProfiles({"test"})
public abstract class IntegrationBaseTest {

    private Logger logger = LoggerFactory.getLogger(IntegrationBaseTest.class);

    @LocalServerPort
    protected Integer port;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private Oauth2Constants oauth2Constants;

    @Autowired
    protected TestRestTemplate testRestTemplate;

    protected OAuth2RestTemplate oauth2StandardClientRestTemplate;

    protected OAuth2RestTemplate oauth2AdminClientRestTemplate;

    @Before
    public void setup() {
        createUsers();

        createCities();

        setupStandardClient();
        setupAdminClient();

        logger.info("Setup complete");
    }

    @After
    public void cleanup() {
        userRepository.deleteAll();
        cityRepository.deleteAll();

        logger.info("Cleanup complete");
    }

    private void createUsers() {
        userRepository.saveAndFlush(new User(
                oauth2Constants.getStandardClientUsername(), passwordEncoder.encode(oauth2Constants.getStandardClientPassword()),
                "userTest@kbc.com", "User", "Test", asList(
                        new Role("STANDARD_USER", "Standard User - Has no admin rights"))));

        userRepository.saveAndFlush(new User(
                oauth2Constants.getAdminClientUsername(), passwordEncoder.encode(oauth2Constants.getAdminClientPassword()),
                "userAdmin@kbc.com", "User", "Admin", asList(
                        new Role("STANDARD_USER", "Standard User - Has no admin rights"),
                        new Role("ADMIN_USER", "Admin User - Has permission to perform admin tasks"))));
    }

    private void createCities() {
        cityRepository.saveAndFlush(new City("Bratislava", "Slovakia"));
        cityRepository.saveAndFlush(new City("Berlin", "Germany"));
        cityRepository.saveAndFlush(new City("Paris", "France"));
        cityRepository.saveAndFlush(new City("Brussels", "Belgium"));
        cityRepository.saveAndFlush(new City("Madrid", "Spain"));
    }

    private void setupStandardClient() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(oauth2Constants.getStandardClientUsername());
        resourceDetails.setPassword(oauth2Constants.getStandardClientPassword());
        resourceDetails.setAccessTokenUri(oauth2Constants.getOauth2TokenUri(port));
        resourceDetails.setClientId(oauth2Constants.getClientId());
        resourceDetails.setClientSecret(oauth2Constants.getClientSecret());
        resourceDetails.setGrantType(oauth2Constants.getGrantType());
        resourceDetails.setScope(oauth2Constants.getScope());

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        this.oauth2StandardClientRestTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        this.oauth2StandardClientRestTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
    }

    private void setupAdminClient() {
        ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceDetails.setUsername(oauth2Constants.getAdminClientUsername());
        resourceDetails.setPassword(oauth2Constants.getAdminClientPassword());
        resourceDetails.setAccessTokenUri(oauth2Constants.getOauth2TokenUri(port));
        resourceDetails.setClientId(oauth2Constants.getClientId());
        resourceDetails.setClientSecret(oauth2Constants.getClientSecret());
        resourceDetails.setGrantType(oauth2Constants.getGrantType());
        resourceDetails.setScope(oauth2Constants.getScope());

        DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

        this.oauth2AdminClientRestTemplate = new OAuth2RestTemplate(resourceDetails, clientContext);
        this.oauth2AdminClientRestTemplate.setMessageConverters(asList(new MappingJackson2HttpMessageConverter()));
    }
}
