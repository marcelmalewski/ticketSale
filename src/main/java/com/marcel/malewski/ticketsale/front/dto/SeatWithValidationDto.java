package com.marcel.malewski.ticketsale.front.dto;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatWithValidationDto {
   private Long id;
   @Max(value = 50, message = "Seat number must be less than 50")
   @Positive(message = "Seat number must be positive")
   @NotNull(message = "Seat number is mandatory")
   private Integer seatNumber;
   @NotNull(message = "Is premium is mandatory")
   private Boolean isPremium;
   @NotNull(message = "Cinema hall is mandatory")
   @Min(value = 1, message = "Cinema hall id must be greater than 0")
   private Long cinemaHallId;

   static public SeatWithValidationDto from(Seat seat) {
      return new SeatWithValidationDto(seat.getId(), seat.getSeatNumber(), seat.getIsPremium(), seat.getCinemaHall().getId());
   }
}
