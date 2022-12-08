package com.marcel.malewski.ticketsale.ticketbuyer.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TicketBuyerNotFoundException extends RuntimeException {
   public TicketBuyerNotFoundException(String message) {
      super(message);
   }
}
