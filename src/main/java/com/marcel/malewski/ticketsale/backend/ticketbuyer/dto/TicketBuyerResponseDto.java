package com.marcel.malewski.ticketsale.backend.ticketbuyer.dto;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;

import java.util.Date;

public class TicketBuyerResponseDto {
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private Date dateOfBirth;
   private String email;
   private AgeRange ageRange;
   private Long loyaltyCardId;
}
