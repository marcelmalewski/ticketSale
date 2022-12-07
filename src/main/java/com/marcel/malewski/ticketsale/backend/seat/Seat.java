package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.seat.dto.SeatWithValidationDto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "seat")
@ToString
@NoArgsConstructor
@AllArgsConstructor
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

   public static Seat from(SeatWithValidationDto seatWithValidationDto, CinemaHall cinemaHall) {
      return new Seat(
              null,
              seatWithValidationDto.getSeatNumber(),
              seatWithValidationDto.getIsPremium(),
              null,
              cinemaHall
      );
   }
}
