package com.marcel.malewski.ticketsale.backend.loyaltycard.dto;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class LoyaltyCardResponseDto {
   private Long id;
   private BigDecimal moneySpent;
   private Long numberOfWatchedMovies;
   private Integer discountOnTheNextTicket;
   private String ticketBuyerName;

   static public List<LoyaltyCardResponseDto> loyaltyCardsResponsDtoFrom(List<LoyaltyCard> loyaltyCards) {
      return loyaltyCards.stream()
              .map(LoyaltyCardResponseDto::from)
              .toList();
   }

   static public LoyaltyCardResponseDto from(LoyaltyCard loyaltyCard) {
      String ticketBuyerName = (loyaltyCard.getTicketBuyer() != null) ? loyaltyCard.getTicketBuyer().getFirstName() : null;

      //Ticket buyera moze nie byc
      return new LoyaltyCardResponseDto(loyaltyCard.getId(), loyaltyCard.getMoneySpent(), loyaltyCard.getNumberOfWatchedMovies(), loyaltyCard.getDiscountOnTheNextTicket(), ticketBuyerName);
   }
}
