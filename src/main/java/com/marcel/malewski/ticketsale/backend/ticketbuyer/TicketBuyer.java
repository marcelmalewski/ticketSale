package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.ticket.Ticket;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;
import com.marcel.malewski.ticketsale.front.dto.TicketBuyerWithValidationDto;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ticket_buyer")
@Setter
@Getter
@ToString
@NoArgsConstructor
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
   private Date dateOfBirth;
   private String email;
   private AgeRange ageRange;
   @OneToOne
   @JoinColumn(name = "loyalty_card_id", referencedColumnName = "id")
   private LoyaltyCard loyaltyCard;
   @ManyToMany(mappedBy = "ticketBuyers")
   @ToString.Exclude
   private List<Ticket> tickets;

   public TicketBuyer(Long id, String firstName, String secondName, String password, Date dateOfBirth, String email, AgeRange ageRange) {
      this.id = id;
      this.firstName = firstName;
      this.secondName = secondName;
      this.password = password;
      this.dateOfBirth = dateOfBirth;
      this.email = email;
      this.ageRange = ageRange;
   }

   public TicketBuyerWithValidationDto toTicketBuyerWithValidationDto() {
      return new TicketBuyerWithValidationDto(
              this.id,
              this.firstName,
              this.secondName,
              this.password,
              this.dateOfBirth,
              this.email,
              this.ageRange.getValue()
      );
   }
}
