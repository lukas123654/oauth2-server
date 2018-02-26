package com.kbc.oauth2Server.controllers.api;

public abstract class PrivateApiController extends ApiController {

    public static final String API_PATH = "/private" + ApiController.API_PATH;

    // controllers paths
    public static final String CITY_URL = API_PATH + "/city";
    public static final String USER_URL = API_PATH + "/user";

}
