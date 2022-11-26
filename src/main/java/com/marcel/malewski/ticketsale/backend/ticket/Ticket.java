package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
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
   private ZonedDateTime showDate;
   // dodac jakąś stałą i ogranicznie do np 20 bo tylko tyle jest sal
   private Integer roomNumber;
   @ManyToMany
   @JoinTable(name = "ticket_ticket_buyer",
           joinColumns = { @JoinColumn(name = "ticket_id", referencedColumnName = "id")},
           inverseJoinColumns = { @JoinColumn(name = "ticket_buyer_id", referencedColumnName = "id")}
   )
   @ToString.Exclude
   //sprawdzenie z liczbą osób jest taka sama jak liczba miejsc
   private Set<TicketBuyer> ticketBuyers;
}
