package com.event.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
  static final long serialVersionUID = 3L;

  public ResourceNotFoundException(String message) {
    super(message);
  }
}
