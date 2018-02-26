package com.kbc.oauth2Server.controllers.api;

public abstract class PublicApiController extends ApiController {

    public static final String API_PATH = "/public" + ApiController.API_PATH;

    // controllers paths
    public static final String PING_URL = API_PATH + "/ping";
    public static final String REGISTRATION_URL = API_PATH + "/registration";

}
