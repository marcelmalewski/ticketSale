package com.marcel.malewski.ticketsale.backend.ticketbuyer.dto;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.front.constraint.EnumConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketBuyerWithValidationDto {
   @NotBlank(message = "First name is mandatory")
   private String firstName;
   @NotBlank(message = "Second name is mandatory")
   private String secondName;
   @NotBlank(message = "Password is mandatory")
   @Length(min = 4, message = "Password must be at least 4 characters long")
   private String password;
   @PastOrPresent(message = "Date of birth must be in the past or present")
   @NotNull(message = "Date of birth is mandatory")
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date dateOfBirth;
   @Email(message = "Email should be valid email address")
   @NotBlank(message = "Email is mandatory")
   private String email;
   @EnumConstraint(regexp = "CHILD|ADULT|SENIOR", message = "Age range have to be one of: CHILD, ADULT, SENIOR")
   private String ageRange;
   @NotNull
   private Long loyaltyCardId;

   static public TicketBuyerWithValidationDto from(TicketBuyer ticketBuyer) {
      Long loyaltyCardId = (ticketBuyer.getLoyaltyCard() != null) ? ticketBuyer.getLoyaltyCard().getId() : null;

      return new TicketBuyerWithValidationDto(
              ticketBuyer.getFirstName(),
              ticketBuyer.getSecondName(),
              ticketBuyer.getPassword(),
              ticketBuyer.getDateOfBirth(),
              ticketBuyer.getEmail(),
              ticketBuyer.getAgeRange().getValue(),
              loyaltyCardId
      );
   }
}
