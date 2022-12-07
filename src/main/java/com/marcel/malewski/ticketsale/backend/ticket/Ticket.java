package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.backend.ticket.dto.TicketWithValidation;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {
   @Id
   @SequenceGenerator(
           name = "ticket_sequence",
           sequenceName = "ticket_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "ticket_sequence"
   )
   private Long id;
   private String movieName;
   private Date showDate;
   //TODO to moglaby by być relacja z obiektem CinemaHall ale nie ma co komplikowac
   private Integer hallNumber;
   @ManyToOne
   @JoinColumn(name = "ticket_buyer_id", nullable = false)
   @ToString.Exclude
   //sprawdzenie z liczbą osób jest taka sama jak liczba miejsc
   private TicketBuyer ticketBuyer;
   @ManyToMany
   @JoinTable(name = "ticket_seat",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "seat_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   private List<Seat> seats;

   static public Ticket from(TicketWithValidation ticketWithValidation, TicketBuyer ticketBuyer, List<Seat> seats) {
      return new Ticket(
              ticketWithValidation.getId(),
              ticketWithValidation.getMovieName(),
              ticketWithValidation.getShowDate(),
              ticketWithValidation.getHallNumber(),
              ticketBuyer,
              seats
      );
   }
}
