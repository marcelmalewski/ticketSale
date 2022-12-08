package com.marcel.malewski.ticketsale.cinemahall.dto;

import com.marcel.malewski.ticketsale.cinemahall.CinemaHall;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
public class CinemaHallWithValidationDto {
   private Long id;
   @Max(value = 20, message = "There are only 20 cinema halls in the cinema")
   @Positive(message = "Cinema hall number must be positive")
   private Integer hallNumber;
   @Positive(message = "Screen width must be positive")
   @Max(value = 20, message = "Screen width cannot be bigger than 20 meters")
   private Integer screenWidthInMeters;
   @Positive(message = "Screen height must be positive")
   @Max(value = 10, message = "Screen height cannot be bigger than 20 meters")
   private Integer screenHeightInMeters;

//   @Length(min = 10, max = 100, message = "Cinema hall name must be between 10 and 100 characters")
//   private String description;

   static public CinemaHallWithValidationDto from(CinemaHall cinemaHall) {
      return new CinemaHallWithValidationDto(
              cinemaHall.getId(),
              cinemaHall.getHallNumber(),
              cinemaHall.getScreenWidthInMeters(),
              cinemaHall.getScreenHeightInMeters()
      );
   }
}
