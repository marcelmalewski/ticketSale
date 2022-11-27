package com.marcel.malewski.ticketsale.backend.seat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SeatNotFoundException extends RuntimeException {
   public SeatNotFoundException(String message) {
      super(message);
   }
}
