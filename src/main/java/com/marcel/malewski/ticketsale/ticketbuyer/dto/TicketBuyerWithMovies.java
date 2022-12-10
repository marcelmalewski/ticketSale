package com.marcel.malewski.ticketsale.ticketbuyer.dto;

import com.marcel.malewski.ticketsale.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.ticket.Ticket;
import com.marcel.malewski.ticketsale.ticketbuyer.agerange.AgeRange;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class TicketBuyerWithMovies {
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private Date dateOfBirth;
   private String email;
   private AgeRange ageRange;
   private LoyaltyCard loyaltyCard;
   private List<Ticket> tickets;
}
