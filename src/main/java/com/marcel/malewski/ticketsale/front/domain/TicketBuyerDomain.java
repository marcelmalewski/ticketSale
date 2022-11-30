package com.marcel.malewski.ticketsale.front.domain;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;
import com.marcel.malewski.ticketsale.front.constraint.EnumConstraint;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Set;

@Data
public class TicketBuyerDomain {
   private Long id;
   @NotBlank(message = "First name is mandatory")
   private String firstName;
   @NotBlank(message = "Second name is mandatory")
   private String secondName;
   @NotBlank(message = "Password is mandatory")
   private String password;
   @PastOrPresent(message = "Date must be in the past or present")
   @NotNull(message = "Date is mandatory")
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date dateOfBirth;
   @Email(message = "Email should be valid")
   @NotBlank(message = "Email is mandatory")
   private String email;
   @EnumConstraint(regexp = "CHILD|ADULT|SENIOR")
   private String ageRange;
   //TODO hmm
   private LoyaltyCard loyaltyCard;
   private Set<Ticket> tickets;

   public TicketBuyerDomain() {
   }
}
