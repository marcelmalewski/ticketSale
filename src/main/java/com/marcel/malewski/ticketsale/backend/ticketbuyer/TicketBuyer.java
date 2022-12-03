package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "ticket_buyer")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TicketBuyer {
   @Id
   @SequenceGenerator(
           name = "ticket_buyer_sequence",
           sequenceName = "ticket_buyer_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "ticket_buyer_sequence"
   )
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private Date dateOfBirth;
   private String email;
   private AgeRange ageRange;
   @OneToOne
   @JoinColumn(name = "loyalty_card_id", referencedColumnName = "id")
   private LoyaltyCard loyaltyCard;
   @ManyToMany(mappedBy = "ticketBuyers")
   @ToString.Exclude
   private Set<Ticket> tickets;
}
