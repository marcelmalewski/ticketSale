package com.marcel.malewski.ticketsale.backend.seat.dto;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SeatResponseDto {
   private Long id;
   private Integer seatNumber;
   private Boolean isPremium;
//   private Set<Ticket> tickets;
   private CinemaHall cinemaHall;

   static public List<SeatResponseDto> seatsResponseDtoFromSeats(List<Seat> seats) {
      return seats.stream()
              .map(SeatResponseDto::from)
              .toList();
   }

static public SeatResponseDto from(Seat seat) {
      return new SeatResponseDto(seat.getId(), seat.getSeatNumber(), seat.getIsPremium(), seat.getCinemaHall());
   }
}
