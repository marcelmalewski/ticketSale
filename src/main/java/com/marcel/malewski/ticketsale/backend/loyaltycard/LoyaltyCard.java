package com.marcel.malewski.ticketsale.backend.loyaltycard;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.backend.loyaltycard.dto.LoyaltyCardWithValidationDto;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "loyalty_card")
@Getter
@Setter
@ToString
@NoArgsConstructor
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

   public LoyaltyCard(Long id, BigDecimal moneySpent, Long numberOfWatchedMovies, Integer discountOnTheNextTicket) {
      this.id = id;
      this.moneySpent = moneySpent;
      this.numberOfWatchedMovies = numberOfWatchedMovies;
      this.discountOnTheNextTicket = discountOnTheNextTicket;
   }

   //uzywane do posta
   //TODO zobaczmy czy tu przy updacie musze podawac Ticekt buyera
   static public LoyaltyCard from(LoyaltyCardWithValidationDto loyaltyCardWithValidationDto) {
      return new LoyaltyCard(
              null,
               loyaltyCardWithValidationDto.getMoneySpent(),
               loyaltyCardWithValidationDto.getNumberOfWatchedMovies(),
               loyaltyCardWithValidationDto.getDiscountOnTheNextTicket()
      );
   }
}
