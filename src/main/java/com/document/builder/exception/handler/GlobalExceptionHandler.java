package com.document.builder.exception.handler;

import com.document.builder.dto.ErrorResponseDTO;
import com.document.builder.exception.UserNotFoundException;
import com.document.builder.exception.UserUnAuthorizedException;
import com.document.builder.exception.UserValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponseDTO userNotFoundException(UserNotFoundException exception){
      log.error("getPropertyNotFoundException {}", exception.getMessage());
      return ErrorResponseDTO.builder()
              .status(HttpStatus.NOT_FOUND.value())
              .message(exception.getMessage())
              .build();
    }


    @ExceptionHandler(UserUnAuthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorResponseDTO userUnAuthorizedException(UserUnAuthorizedException exception){
        log.error("getPropertyNotFoundException {}", exception.getMessage());
        return ErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler(UserValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponseDTO userValidationException(UserValidationException exception){
        log.error("getPropertyNotFoundException {}", exception.getMessage());
        return ErrorResponseDTO.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(exception.getMessage())
                .build();
    }

}
