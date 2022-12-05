package com.marcel.malewski.ticketsale.backend.cinemahall.dto;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CinemaHallResponseDto {
   private Long id;
   private Integer hallNumber;
   private List<Long> seatsIds;

   static public List<CinemaHallResponseDto> cinemaHallsResponseDtoFrom(List<CinemaHall> cinemaHalls) {
      return cinemaHalls.stream()
              .map(CinemaHallResponseDto::from)
              .toList();
   }

   static public CinemaHallResponseDto from(CinemaHall cinemaHall) {
      List<Long> seatsIds = cinemaHall.getSeats().stream()
              .map(Seat::getId)
              .toList();
      return new CinemaHallResponseDto(cinemaHall.getId(), cinemaHall.getHallNumber(), seatsIds);
   }
}
