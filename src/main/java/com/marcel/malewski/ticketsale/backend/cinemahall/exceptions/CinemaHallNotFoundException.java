package com.marcel.malewski.ticketsale.backend.cinemahall.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CinemaHallNotFoundException extends RuntimeException {
   public CinemaHallNotFoundException(String message) {
      super(message);
   }
}
