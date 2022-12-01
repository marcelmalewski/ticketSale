package com.marcel.malewski.ticketsale.front.dto;


import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class LoyaltyCardWithValidationDto {
   @Min(value = 0, message = "Money spent must be greater than or equal to 0")
   @NotNull
   private BigDecimal moneySpent;
   @Min(value = 0, message = "Number of watched movies must be greater than or equal to 0")
   @NotNull
   private Long numberOfWatchedMovies;
   @Max(value = 100, message = "Discount must be less than 100")
   @Min(value = 0, message = "Discount must be greater than or equal to 0")
   @NotNull
   private Integer discountOnTheNextTicket;

   public LoyaltyCard toLoyaltyCard() {
         LoyaltyCard loyaltyCard = new LoyaltyCard();
         loyaltyCard.setMoneySpent(moneySpent);
         loyaltyCard.setNumberOfWatchedMovies(numberOfWatchedMovies);
         loyaltyCard.setDiscountOnTheNextTicket(discountOnTheNextTicket);
         return loyaltyCard;
   }
}
