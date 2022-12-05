package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.front.dto.SeatWithValidationDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "seat")
@ToString
@NoArgsConstructor
public class Seat {
   @Id
   @SequenceGenerator(
           name = "seat_sequence",
           sequenceName = "seat_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "seat_sequence"
   )
   private Long id;
   private Integer seatNumber;
   private Boolean isPremium;
   @ManyToMany(mappedBy = "seats")
   @ToString.Exclude
   private List<Ticket> tickets;
   @ManyToOne
   @JoinColumn(name = "cinema_hall_id", nullable = false)
   //w zakresie jeden sali nie moze sie powtarzac seatNumber
   private CinemaHall cinemaHall;

   public Seat(Long id, Integer seatNumber, Boolean isPremium, CinemaHall cinemaHall) {
      this.id = id;
      this.seatNumber = seatNumber;
      this.isPremium = isPremium;
      this.cinemaHall = cinemaHall;
   }

   public static Seat from(SeatWithValidationDto seatWithValidationDto, CinemaHall cinemaHall) {
      return new Seat(
              seatWithValidationDto.getId(),
              seatWithValidationDto.getSeatNumber(),
              seatWithValidationDto.getIsPremium(),
              cinemaHall
      );
   }
}
