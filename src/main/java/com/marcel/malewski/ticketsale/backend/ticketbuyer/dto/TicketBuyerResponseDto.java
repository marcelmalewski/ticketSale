package com.marcel.malewski.ticketsale.backend.ticketbuyer.dto;

import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
public class TicketBuyerResponseDto {
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private Date dateOfBirth;
   private String email;
   private String ageRange;
   private Long loyaltyCardId;
   private List<String> ticketsMovieNames;

   static public List<TicketBuyerResponseDto> ticketBuyersResponseDtoFrom(List<TicketBuyer> ticketBuyers) {
      return ticketBuyers.stream()
              .map(TicketBuyerResponseDto::from)
              .toList();
   }

   static public TicketBuyerResponseDto from(TicketBuyer ticketBuyer) {
      //LoyaltyCard moze nie byc
      //Tickets moze nie byc
      Long loyaltyCardId = (ticketBuyer.getLoyaltyCard() != null) ? ticketBuyer.getLoyaltyCard().getId() : null;
      List<String> ticketsMovieNames = (ticketBuyer.getTickets().isEmpty()) ?
              Collections.<String>emptyList() :
              getTicketsMovieNamesFromTickets(ticketBuyer.getTickets());

      return new TicketBuyerResponseDto(
              ticketBuyer.getId(),
              ticketBuyer.getFirstName(),
              ticketBuyer.getSecondName(),
              ticketBuyer.getPassword(),
              ticketBuyer.getDateOfBirth(),
              ticketBuyer.getEmail(),
              ticketBuyer.getAgeRange().getValue(),
              loyaltyCardId,
              ticketsMovieNames
      );
   }

   static private List<String> getTicketsMovieNamesFromTickets(List<Ticket> tickets) {
      return tickets.stream()
              .map(Ticket::getMovieName)
              .toList();
   }
}
