package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "seat")
@ToString
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
   //w zakresie jeden sali nie moze sie powtarzac seatNumber
   private Integer seatNumber;
   private Boolean isPremium;
   @ManyToMany(mappedBy = "seats")
   @ToString.Exclude
   private Set<Ticket> tickets;
   @ManyToOne
   @JoinColumn(name = "cinema_hall_id", nullable = false)
   private CinemaHall cinemaHall;
}
