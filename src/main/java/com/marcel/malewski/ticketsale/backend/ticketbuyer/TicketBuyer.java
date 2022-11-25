package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table
@Getter
@Setter
@ToString
public class TicketBuyer {
   @Id
   @SequenceGenerator(
           name = "ticket_buyer_sequence",
           sequenceName = "ticket_buyer_sequence",
           allocationSize = 1
   )
   @GeneratedValue(
           strategy = GenerationType.SEQUENCE,
           generator = "ticket_buyer_sequence"
   )
   private Long id;
   private String firstName;
   private String secondName;
   private String password;
   private ZonedDateTime dateOfBirth;

   public TicketBuyer() {
   }

   public TicketBuyer(String firstName, String secondName, String password, ZonedDateTime dateOfBirth) {
      this.firstName = firstName;
      this.secondName = secondName;
      this.password = password;
      this.dateOfBirth = dateOfBirth;
   }

   public TicketBuyer(Long id, String firstName, String secondName, String password, ZonedDateTime dateOfBirth) {
      this.id = id;
      this.firstName = firstName;
      this.secondName = secondName;
      this.password = password;
      this.dateOfBirth = dateOfBirth;
   }


}
