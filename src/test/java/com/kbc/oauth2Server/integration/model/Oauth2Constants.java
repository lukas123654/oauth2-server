package com.kbc.oauth2Server.integration.model;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Component
@Getter
public class Oauth2Constants {

    @Value("${server.context-path}")
    private String serverPath;

    @Value("${security.jwt.client-id}")
    private String clientId;

    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    private String grantType = "password";

    private List<String> scope = asList("read", "write");

    private String standardClientUsername = "user.test";

    private String standardClientPassword = "test";

    private String adminClientUsername = "user.admin";

    private String adminClientPassword = "admin";

    public String getOauth2TokenUri(Integer port) {
        return format("http://localhost:%d%s/oauth/token", port, serverPath);
    }
}
