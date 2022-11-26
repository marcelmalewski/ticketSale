package com.marcel.malewski.ticketsale.backend.loyaltycard.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LoyaltyCardNotFoundException extends RuntimeException {
   public LoyaltyCardNotFoundException(String message) {
      super(message);
   }
}
