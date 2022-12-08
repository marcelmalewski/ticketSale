package com.marcel.malewski.ticketsale.ticketbuyer.dto;

import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.constraint.EnumConstraint;
import com.marcel.malewski.ticketsale.ticketbuyer.agerange.AgeRange;
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
   @Pattern(
           regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$",
           message = "Password must contain at least one uppercase letter, one lowercase letter, one number and one special character, min 8, max 20 characters"
   )
   private String password;
   @PastOrPresent(message = "Date of birth must be in the past or present")
   @NotNull(message = "Date of birth is mandatory")
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private Date dateOfBirth;
   @Email(message = "Email should be valid email address")
   @NotBlank(message = "Email is mandatory")
   private String email;
   @EnumConstraint(enumClass = AgeRange.class, message = "Age range have to be one of: CHILD, ADULT, SENIOR")
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
