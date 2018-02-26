package com.kbc.oauth2Server.controllers.api.publicApi;

import com.kbc.oauth2Server.controllers.api.ApiController;
import com.kbc.oauth2Server.controllers.api.PublicApiController;
import com.kbc.oauth2Server.model.response.PingResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kbc.oauth2Server.controllers.api.PublicApiController.PING_URL;


@RestController
@RequestMapping(PING_URL)
public class PingController extends PublicApiController {

    @ApiOperation(value = "Check if server is running", tags = ApiController.PING_TAG)
    @GetMapping
    public PingResponse ping() {
        return new PingResponse(true);
    }

}
