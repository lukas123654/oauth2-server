package com.kbc.oauth2Server.controllers.api;

public abstract class ApiController {

    public static final String API_PATH = "/api/v1/";

    // Spring Boot Actuator services
    public static final String ACTUATOR_AUTOCONFIG_ENDPOINT = "/autoconfig";
    public static final String ACTUATOR_BEANS_ENDPOINT = "/beans";
    public static final String ACTUATOR_CONFIGPROPS_ENDPOINT = "/configprops";
    public static final String ACTUATOR_ENV_ENDPOINT = "/env";
    public static final String ACTUATOR_MAPPINGS_ENDPOINT = "/mappings";
    public static final String ACTUATOR_METRICS_ENDPOINT = "/metrics";
    public static final String ACTUATOR_SHUTDOWN_ENDPOINT = "/shutdown";

    // Swagger implicit params
    public static final String header = "header";
    public static final String authHeader = "Authorization";
    public static final String authHeaderValue = "Bearer ${access_token}";

    // Swagger tags
    public static final String CITY_TAG = "City";
    public static final String USER_TAG = "User";
    public static final String PING_TAG = "Ping";
    public static final String REGISTER_TAG = "Registration";
}
