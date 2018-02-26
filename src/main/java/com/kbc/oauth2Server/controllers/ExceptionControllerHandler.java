package com.kbc.oauth2Server.controllers;

import com.kbc.oauth2Server.exceptions.DeleteAdminException;
import com.kbc.oauth2Server.exceptions.JpaCreateException;
import com.kbc.oauth2Server.exceptions.JpaFindException;
import com.kbc.oauth2Server.integration.oauth2.model.response.ErrorResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionControllerHandler {

    private Logger logger = LoggerFactory.getLogger(ExceptionControllerHandler.class);

    @ExceptionHandler(value = JpaCreateException.class)
    public ResponseEntity<ErrorResponse> handleJpaCreateException(HttpServletRequest request, JpaCreateException exception) {
        logger.info("Request: {} raised: {}", request.getRequestURL(), exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage(), null, null));
    }

    @ExceptionHandler(value = JpaFindException.class)
    public ResponseEntity<ErrorResponse> handleJpaFindException(HttpServletRequest request, JpaFindException exception) {
        logger.info("Request: {} raised: {}", request.getRequestURL(), exception.getMessage());
        return ResponseEntity.badRequest().body(new ErrorResponse(exception.getMessage(), null, null));
    }

    @ExceptionHandler(value = DeleteAdminException.class)
    public ResponseEntity<ErrorResponse> handleDeleteAdminException(HttpServletRequest request, DeleteAdminException exception) {
        logger.info("Request {} raised: {}", request.getRequestURL(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse(exception.getMessage(), null, null));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(HttpServletRequest request, AccessDeniedException exception) {
        logger.info("Request {} raised: {}", request.getRequestURL(), exception.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErrorResponse("Access denied", null,
                "User doesn't have role ADMIN_USER"));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            HttpServletRequest request, MethodArgumentNotValidException exception) {
        logger.info("Request: {} raised: {}", request.getRequestURL(), exception.getMessage());

        StringWrapper errorMsgWrapper = new StringWrapper();
        exception.getBindingResult().getFieldErrors().stream().forEach(
                fieldError -> errorMsgWrapper.appendValue("Field " + fieldError.getField() + " in "
                        + fieldError.getObjectName() + " " + fieldError.getDefaultMessage() + ", "));
        String errorMsg = errorMsgWrapper.getValue().substring(0, errorMsgWrapper.getValue().length()-2);

        return ResponseEntity.badRequest().body(new ErrorResponse("Request parameter not valid", null, errorMsg));
    }

    @Getter
    @NoArgsConstructor
    private class StringWrapper {

        private String value = "";

        public StringWrapper appendValue(String value) {
            this.value += value;
            return this;
        }
    }

}
