package com.marcel.malewski.ticketsale.backend.ticket.dto;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
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
   private List<Long> seatsIds;

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
              seatsIdsFromSeats(ticket.getSeats())
      );
   }

   static private List<Long> seatsIdsFromSeats(List<Seat> seats) {
      return seats.stream()
              .map(Seat::getId)
              .toList();
   }
}
