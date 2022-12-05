package com.marcel.malewski.ticketsale.backend.loyaltycard.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LoyaltyCardResponseDto {
   private Long id;
   private BigDecimal moneySpent;
   private Long numberOfWatchedMovies;
   private Integer discountOnTheNextTicket;
   private Long ticketBuyerId;
}
