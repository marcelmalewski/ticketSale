package com.marcel.malewski.ticketsale.backend.seat.dto;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeatResponseDto {
   private Long id;
   private Integer seatNumber;
   private Boolean isPremium;
   private List<Long> ticketsIds;
   private CinemaHall cinemaHall;

   static public List<SeatResponseDto> seatsResponseDtoFrom(List<Seat> seats) {
      return seats.stream()
              .map(SeatResponseDto::from)
              .toList();
   }

   static public SeatResponseDto from(Seat seat) {
         return new SeatResponseDto(
                 seat.getId(),
                 seat.getSeatNumber(),
                 seat.getIsPremium(),
                 ticketsIdsFromTickets(seat.getTickets()),
                 seat.getCinemaHall()
         );
   }

   static private List<Long> ticketsIdsFromTickets(List<Ticket> tickets) {
      return tickets.stream()
              .map(Ticket::getId)
              .toList();
   }
}
