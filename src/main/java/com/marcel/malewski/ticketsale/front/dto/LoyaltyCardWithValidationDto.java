package com.marcel.malewski.ticketsale.front.dto;


import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoyaltyCardWithValidationDto {
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

   public static LoyaltyCardWithValidationDto from(LoyaltyCard loyaltyCard) {
         return new LoyaltyCardWithValidationDto(
                   loyaltyCard.getMoneySpent(),
                   loyaltyCard.getNumberOfWatchedMovies(),
                   loyaltyCard.getDiscountOnTheNextTicket()
         );
   }
}
