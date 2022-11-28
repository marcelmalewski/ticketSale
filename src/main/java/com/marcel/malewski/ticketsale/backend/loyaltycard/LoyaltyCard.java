package com.marcel.malewski.ticketsale.backend.loyaltycard;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
   @Min(value = 0, message = "Money spent must be greater than or equal to 0")
   private BigDecimal moneySpent;
   @Min(value = 0, message = "Number of watched movies must be greater than or equal to 0")
   private Long numberOfWatchedMovies;
   @Max(value = 100, message = "Discount must be less than 100")
   @Min(value = 0, message = "Discount must be greater than or equal to 0")
   private Integer discountOnTheNextTicket;
   @OneToOne(mappedBy = "loyaltyCard")
   private TicketBuyer ticketBuyer;
}
