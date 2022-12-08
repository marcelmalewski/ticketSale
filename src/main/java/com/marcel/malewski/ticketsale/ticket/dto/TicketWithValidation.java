package com.marcel.malewski.ticketsale.ticket.dto;

import com.marcel.malewski.ticketsale.seat.Seat;
import com.marcel.malewski.ticketsale.ticket.Ticket;
import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketWithValidation {
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
   @NotNull(message = "Ticket buyer id must be provided")
   private Long ticketBuyerId;
   @NotBlank(message = "Seats ids must be provided")
   private String seatsIds;

   static public TicketWithValidation from(Ticket ticket) {
      return new TicketWithValidation(
              ticket.getMovieName(),
              ticket.getShowDate(),
              ticket.getHallNumber(),
              getTicketBuyerId(ticket.getTicketBuyer()),
              getSeatsIds(ticket.getSeats())
      );
   }

   static private Long getTicketBuyerId(TicketBuyer ticketBuyer) {
      return ticketBuyer.getId();
   }

   static private String getSeatsIds(List<Seat> seats) {
      return seats.stream()
              .map(seat -> seat.getId().toString())
              .collect(Collectors.joining(" "));
   }
}
