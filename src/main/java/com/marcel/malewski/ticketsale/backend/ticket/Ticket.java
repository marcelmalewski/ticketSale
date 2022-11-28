package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Positive;
import java.time.ZonedDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ticket")
@ToString
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
   @Future(message = "Date must be in the future")
   private ZonedDateTime showDate;
   @Max(value = 20, message = "There are only 20 cinema halls in the cinema")
   @Positive(message = "Cinema hall number must be positive")
   private Integer hallNumber;
   @ManyToMany
   @JoinTable(name = "ticket_ticket_buyer",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "ticket_buyer_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   //sprawdzenie z liczbą osób jest taka sama jak liczba miejsc
   private Set<TicketBuyer> ticketBuyers;
   @ManyToMany
   @JoinTable(name = "ticket_seat",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "seat_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   private Set<Seat> seats;
}
