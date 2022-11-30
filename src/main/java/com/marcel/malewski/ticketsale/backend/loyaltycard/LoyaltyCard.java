package com.marcel.malewski.ticketsale.backend.loyaltycard;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loyalty_card")
@Getter
@Setter
@ToString
public class LoyaltyCard {
   @Id
   @SequenceGenerator(
           name = "loyalty_card_sequence",
           sequenceName = "loyalty_card_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "loyalty_card_sequence"
   )
   private Long id;
   private BigDecimal moneySpent;
   private Long numberOfWatchedMovies;
   private Integer discountOnTheNextTicket;
   @OneToOne(mappedBy = "loyaltyCard")
   private TicketBuyer ticketBuyer;
}
