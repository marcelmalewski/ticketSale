package com.marcel.malewski.ticketsale.front.dto;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;
import com.marcel.malewski.ticketsale.front.constraint.EnumConstraint;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class TicketBuyerWithValidationDto {
   private Long id;
   @NotBlank(message = "First name is mandatory")
   private String firstName;
   @NotBlank(message = "Second name is mandatory")
   private String secondName;
   @NotBlank(message = "Password is mandatory")
   @Length(min = 4, message = "Password must be at least 4 characters long")
   private String password;
   @PastOrPresent(message = "Date of birth must be in the past or present")
   @NotNull(message = "Date of birth is mandatory")
   private Date dateOfBirth;
   @Email(message = "Email should be valid")
   @NotBlank(message = "Email is mandatory")
   private String email;
   //@EnumConstraint(regexp = "CHILD|ADULT|SENIOR", message = "Age range must be one of: CHILD, ADULT, SENIOR")
   private AgeRange ageRange;

   public TicketBuyerWithValidationDto() {
   }
}
