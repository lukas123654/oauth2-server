package com.kbc.oauth2Server.integration.oauth2;

import com.kbc.oauth2Server.integration.IntegrationBaseTest;
import org.junit.Test;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import static org.assertj.core.api.Assertions.assertThat;

public class Oauth2TokenTest extends IntegrationBaseTest {

    @Test
    public void validStandardUserAccessToken() {
        OAuth2AccessToken oAuth2AccessToken = oauth2StandardClientRestTemplate.getAccessToken();

        assertThat(oAuth2AccessToken.getValue()).isNotEmpty();
        assertThat(oAuth2AccessToken.getRefreshToken().getValue()).isNotEmpty();
        assertThat(oAuth2AccessToken.getTokenType()).isEqualToIgnoringCase("bearer");
    }

    @Test
    public void validAdminUserAccessToken() {
        OAuth2AccessToken oAuth2AccessToken = oauth2AdminClientRestTemplate.getAccessToken();

        assertThat(oAuth2AccessToken.getValue()).isNotEmpty();
        assertThat(oAuth2AccessToken.getRefreshToken().getValue()).isNotEmpty();
        assertThat(oAuth2AccessToken.getTokenType()).isEqualToIgnoringCase("bearer");
    }

}
