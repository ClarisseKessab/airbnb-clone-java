package com.clarissek.airbnb_clone.exception;

public class ResourceNotFoundException extends RuntimeException {

  public ResourceNotFoundException(String message){
    super(message);
  }

}
