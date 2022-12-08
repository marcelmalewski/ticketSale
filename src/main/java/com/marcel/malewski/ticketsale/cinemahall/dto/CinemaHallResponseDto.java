package com.marcel.malewski.ticketsale.cinemahall.dto;

import com.marcel.malewski.ticketsale.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CinemaHallResponseDto {
   private Long id;
   private Integer hallNumber;
   private List<Long> seatsIds;
   private Integer screenWidthInMeters;
   private Integer screenHeightInMeters;

   static public List<CinemaHallResponseDto> cinemaHallsResponseDtoFrom(List<CinemaHall> cinemaHalls) {
      return cinemaHalls.stream()
              .map(CinemaHallResponseDto::from)
              .toList();
   }

   static public CinemaHallResponseDto from(CinemaHall cinemaHall) {
      List<Long> seatsIds = cinemaHall.getSeats().stream()
              .map(Seat::getId)
              .toList();
      return new CinemaHallResponseDto(
              cinemaHall.getId(),
              cinemaHall.getHallNumber(),
              seatsIds,
              cinemaHall.getScreenWidthInMeters(),
              cinemaHall.getScreenHeightInMeters()
      );
   }
}
