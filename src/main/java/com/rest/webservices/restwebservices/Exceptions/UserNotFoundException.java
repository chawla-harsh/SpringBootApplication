package com.rest.webservices.restwebservices.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * // TODO Comment
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

  public UserNotFoundException(String message) {
    super(message);
  }
}
