package com.marcel.malewski.ticketsale.backend.cinemahall;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Getter
@Entity
@Table(name = "cinema_hall")
@Setter
@ToString
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
   @Max(value = 20, message = "There are only 20 cinema halls in the cinema")
   @Positive(message = "Cinema hall number must be positive")
   private Integer hallNumber;
   @OneToMany(mappedBy = "cinemaHall")
   @Size(max = 50, message = "Cinema hall cannot have more than 50 seats")
   @ToString.Exclude
   //czy jak powstaje miejsce i dodaje do siebie id cinema hall i bedzie to 51 to nie bedzie bledu?
   private Set<Seat> seats;
}
