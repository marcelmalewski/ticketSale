package com.marcel.malewski.ticketsale.ticket.dto;

import com.marcel.malewski.ticketsale.seat.Seat;
import com.marcel.malewski.ticketsale.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketResponseDto {
   private Long id;
   private String movieName;
   private Date showDate;
   private Integer hallNumber;
   private String ticketBuyerName;
   private List<Integer> seatsNumbers;

   static public List<TicketResponseDto> ticketsResponseDtoFrom(List<Ticket> tickets) {
      return tickets.stream()
              .map(TicketResponseDto::from)
              .toList();
   }

   static public TicketResponseDto from(Ticket ticket) {
      return new TicketResponseDto(
              ticket.getId(),
              ticket.getMovieName(),
              ticket.getShowDate(),
              ticket.getHallNumber(),
              ticket.getTicketBuyer().getFirstName() + " " + ticket.getTicketBuyer().getSecondName(),
              seatsNumberFromSeats(ticket.getSeats())
      );
   }

   static private List<Integer> seatsNumberFromSeats(List<Seat> seats) {
      return seats.stream()
              .map(Seat::getSeatNumber)
              .toList();
   }
}
