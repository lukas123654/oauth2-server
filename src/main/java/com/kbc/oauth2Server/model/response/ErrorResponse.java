package com.kbc.oauth2Server.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private String message;

    private PostAction postAction;

    private String additionalInfo;

    public enum PostAction {
        LOG_OFF, DEACTIVATE
    }
}
