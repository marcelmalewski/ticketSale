package com.marcel.malewski.ticketsale.front.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class TicketWithValidation {
   private Long id;
   @NotBlank
   private String movieName;
   @Future(message = "Date must be in the future")
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   @NotNull(message = "Show date must be provided")
   private Date showDate;
   @Max(value = 20, message = "There are only 20 cinema halls in the cinema")
   @Positive(message = "Cinema hall number must be positive")
   @NotNull(message = "Cinema hall number must be provided")
   private Integer hallNumber;
   //sprawdzenie z liczbą osób jest taka sama jak liczba miejsc
   @NotBlank(message = "Ticket buyers ids must be provided")
   private String ticketBuyersIds;
   @NotBlank(message = "Seats ids must be provided")
   private String seatsIds;
}
