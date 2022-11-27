package com.marcel.malewski.ticketsale.backend.cinemahall;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
   //TODO moze faktycznie dac tu potem mape data film
   //TODO w adnotacjach mozna dac ograniczenia wilosci
   //private ZonedDateTime[] showDates;
   @Max(value = 20, message = "There are only 20 cinema halls in the cinema")
   @Min(value = 1, message = "Lowest cinema hall number is 1")
   private Integer hallNumber;
   @OneToMany(mappedBy = "cinemaHall")
   @ToString.Exclude
   private Set<Seat> seats;
}
