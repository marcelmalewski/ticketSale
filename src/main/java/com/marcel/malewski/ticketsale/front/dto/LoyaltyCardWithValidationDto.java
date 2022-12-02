package com.marcel.malewski.ticketsale.front.dto;


import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LoyaltyCardWithValidationDto {
   private Long id;
   @Min(value = 0, message = "Money spent must be greater than or equal to 0")
   @NotNull(message = "Money spent is mandatory")
   private BigDecimal moneySpent;
   @Min(value = 0, message = "Number of watched movies must be greater than or equal to 0")
   @NotNull(message = "Number of watched movies is mandatory")
   private Long numberOfWatchedMovies;
   @Max(value = 100, message = "Discount must be less than 100")
   @Min(value = 0, message = "Discount must be greater than or equal to 0")
   @NotNull(message = "Discount is mandatory")
   private Integer discountOnTheNextTicket;

   public LoyaltyCardWithValidationDto() {
   }

   public LoyaltyCard toLoyaltyCard() {
         LoyaltyCard loyaltyCard = new LoyaltyCard();
         loyaltyCard.setId(this.id);
         loyaltyCard.setMoneySpent(this.moneySpent);
         loyaltyCard.setNumberOfWatchedMovies(this.numberOfWatchedMovies);
         loyaltyCard.setDiscountOnTheNextTicket(this.discountOnTheNextTicket);
         return loyaltyCard;
   }
}
