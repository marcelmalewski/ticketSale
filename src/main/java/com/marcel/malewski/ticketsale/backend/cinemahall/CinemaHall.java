package com.marcel.malewski.ticketsale.backend.cinemahall;

import com.marcel.malewski.ticketsale.backend.cinemahall.dto.CinemaHallWithValidationDto;
import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "cinema_hall")
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CinemaHall {
   @Id
   @SequenceGenerator(
           name = "cinema_hall_sequence",
           sequenceName = "cinema_hall_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "cinema_hall_sequence"
   )
   private Long id;
   private Integer hallNumber;
   private Integer screenWidthInMeters;
   private Integer screenHeightInMeters;
   @OneToMany(mappedBy = "cinemaHall")
   @ToString.Exclude
   //czy jak powstaje miejsce i dodaje do siebie id cinema hall i bedzie to 51 to nie bedzie bledu?
   private List<Seat> seats;

   static public CinemaHall from(CinemaHallWithValidationDto cinemaHallWithValidationDto) {
      return new CinemaHall(
              cinemaHallWithValidationDto.getId(),
              cinemaHallWithValidationDto.getHallNumber(),
              cinemaHallWithValidationDto.getScreenWidthInMeters(),
              cinemaHallWithValidationDto.getScreenHeightInMeters(),
              null
      );
   }
}
