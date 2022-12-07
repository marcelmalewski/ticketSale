package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCardRepository;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.agerange.AgeRange;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.dto.TicketBuyerResponseDto;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.exceptions.TicketBuyerNotFoundException;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.dto.TicketBuyerWithValidationDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyerConstants.TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE;

@Service
public class TicketBuyerService {
   private final TicketBuyerRepository ticketBuyerRepository;
   private final LoyaltyCardRepository loyaltyCardRepository;

   public TicketBuyerService(TicketBuyerRepository ticketBuyerRepository, LoyaltyCardRepository loyaltyCardRepository) {
      this.ticketBuyerRepository = ticketBuyerRepository;
      this.loyaltyCardRepository = loyaltyCardRepository;
   }

   public List<TicketBuyerResponseDto> getAllTicketBuyers() {
      List<TicketBuyer> ticketBuyers = this.ticketBuyerRepository.findAll();
      return TicketBuyerResponseDto.ticketBuyersResponseDtoFrom(ticketBuyers);
   }

   public TicketBuyerWithValidationDto getTicketBuyerValidationById(long id) {
      TicketBuyer ticketBuyer = this.ticketBuyerRepository.findById(id).orElseThrow(
              () -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));
      return TicketBuyerWithValidationDto.from(ticketBuyer);
   }

//   public TicketBuyer getTicketBuyerById(long id) {
//      return this.ticketBuyerRepository.findById(id).orElseThrow(() -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));
//   }

   public void postTicketBuyer(TicketBuyerWithValidationDto ticketBuyerWithValidationDto) {
      LoyaltyCard loyaltyCard = this.loyaltyCardRepository.getReferenceById(
              ticketBuyerWithValidationDto.getLoyaltyCardId()
      );

      TicketBuyer ticketBuyer = TicketBuyer.from(ticketBuyerWithValidationDto, loyaltyCard);
      this.ticketBuyerRepository.save(ticketBuyer);
   }

   public void putTicketBuyerById(long id, TicketBuyerWithValidationDto ticketBuyerWithValidationDto) {
      TicketBuyer ticketBuyer = this.ticketBuyerRepository.findById(id).orElseThrow(
              () -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id))
      );
      LoyaltyCard loyaltyCard = this.loyaltyCardRepository.getReferenceById(ticketBuyerWithValidationDto.getLoyaltyCardId());

      ticketBuyer.setFirstName(ticketBuyerWithValidationDto.getFirstName());
      ticketBuyer.setSecondName(ticketBuyerWithValidationDto.getSecondName());
      ticketBuyer.setPassword(ticketBuyerWithValidationDto.getPassword());
      ticketBuyer.setDateOfBirth(ticketBuyerWithValidationDto.getDateOfBirth());
      ticketBuyer.setAgeRange(AgeRange.valueOf(ticketBuyerWithValidationDto.getAgeRange()));
      ticketBuyer.setLoyaltyCard(loyaltyCard);

      this.ticketBuyerRepository.save(ticketBuyer);
   }

//   public TicketBuyer patchTicketBuyerById(long id, TicketBuyer ticketBuyer) {
//      TicketBuyer currentTicketBuyer = this.ticketBuyerRepository.findById(id)
//              .orElseThrow(() -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));
//
//      currentTicketBuyer.setFirstName(
//              (ticketBuyer.getFirstName() != null) ? ticketBuyer.getFirstName() : currentTicketBuyer.getFirstName()
//      );
//      currentTicketBuyer.setSecondName(
//              (ticketBuyer.getSecondName() != null) ? ticketBuyer.getSecondName() : currentTicketBuyer.getSecondName()
//      );
//      currentTicketBuyer.setPassword(
//              (ticketBuyer.getPassword() != null) ? ticketBuyer.getPassword() : currentTicketBuyer.getPassword()
//      );
//      currentTicketBuyer.setDateOfBirth(
//              (ticketBuyer.getDateOfBirth() != null) ? ticketBuyer.getDateOfBirth() : currentTicketBuyer.getDateOfBirth()
//      );
//      currentTicketBuyer.setEmail(
//              (ticketBuyer.getEmail() != null) ? ticketBuyer.getEmail() : currentTicketBuyer.getEmail()
//      );
//      currentTicketBuyer.setAgeRange(
//              (ticketBuyer.getAgeRange() != null) ? ticketBuyer.getAgeRange() : currentTicketBuyer.getAgeRange()
//      );
//      currentTicketBuyer.setLoyaltyCard(
//              (ticketBuyer.getLoyaltyCard() != null) ? ticketBuyer.getLoyaltyCard() : currentTicketBuyer.getLoyaltyCard()
//      );
//
//      return this.ticketBuyerRepository.save(currentTicketBuyer);
//   }

   public void deleteTicketBuyerById(long id) {
      if (!this.ticketBuyerRepository.existsById(id))
         throw new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id));

      this.ticketBuyerRepository.deleteById(id);
   }
}
