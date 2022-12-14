package com.marcel.malewski.ticketsale.loyaltycard;

import com.marcel.malewski.ticketsale.loyaltycard.dto.LoyaltyCardResponseDto;
import com.marcel.malewski.ticketsale.loyaltycard.exceptions.LoyaltyCardNotFoundException;
import com.marcel.malewski.ticketsale.loyaltycard.dto.LoyaltyCardWithValidationDto;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.loyaltycard.LoyaltyCardConstants.LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE;

@Service
public class LoyaltyCardService {
   private final LoyaltyCardRepository loyaltyCardRepository;

   public LoyaltyCardService(LoyaltyCardRepository loyaltyCardRepository) {
      this.loyaltyCardRepository = loyaltyCardRepository;
   }

   public List<LoyaltyCardResponseDto> getAllLoyaltyCards() {
      List<LoyaltyCard> loyaltyCards = this.loyaltyCardRepository.findAll();
      return LoyaltyCardResponseDto.loyaltyCardsResponsDtoFrom(loyaltyCards);
   }

   public LoyaltyCardWithValidationDto getLoyaltyCardWithValidationById(long id) {
      LoyaltyCard loyaltyCard = this.loyaltyCardRepository.findById(id)
              .orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));
      return LoyaltyCardWithValidationDto.from(loyaltyCard);
   }

//   public LoyaltyCard getLoyaltyCardById(long id) {
//      return this.loyaltyCardRepository.findById(id).orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));
//   }

   public void postLoyaltyCard(LoyaltyCardWithValidationDto loyaltyCardWithValidationDto) {
      LoyaltyCard loyaltyCard = LoyaltyCard.from(loyaltyCardWithValidationDto);
      this.loyaltyCardRepository.save(loyaltyCard);
   }

   public void putLoyaltyCardById(long id, LoyaltyCardWithValidationDto loyaltyCardWithValidationDto) {
      LoyaltyCard loyaltyCardToUpdate = this.loyaltyCardRepository.findById(id)
              .orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));

      loyaltyCardToUpdate.setMoneySpent(loyaltyCardWithValidationDto.getMoneySpent());
      loyaltyCardToUpdate.setNumberOfWatchedMovies(loyaltyCardWithValidationDto.getNumberOfWatchedMovies());
      loyaltyCardToUpdate.setDiscountOnTheNextTicket(loyaltyCardWithValidationDto.getDiscountOnTheNextTicket());

      this.loyaltyCardRepository.save(loyaltyCardToUpdate);
   }

//   public LoyaltyCard patchLoyaltyCardById(long id, LoyaltyCard loyaltyCard) {
//      LoyaltyCard currentLoyaltyCard = this.loyaltyCardRepository.findById(id)
//              .orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));
//
//      currentLoyaltyCard.setMoneySpent(
//              (loyaltyCard.getMoneySpent() != null) ? loyaltyCard.getMoneySpent() : currentLoyaltyCard.getMoneySpent()
//      );
//      currentLoyaltyCard.setNumberOfWatchedMovies(
//              (loyaltyCard.getNumberOfWatchedMovies() != null) ? loyaltyCard.getNumberOfWatchedMovies() : currentLoyaltyCard.getNumberOfWatchedMovies()
//      );
//      currentLoyaltyCard.setDiscountOnTheNextTicket(
//              (loyaltyCard.getDiscountOnTheNextTicket() != null) ? loyaltyCard.getDiscountOnTheNextTicket() : currentLoyaltyCard.getDiscountOnTheNextTicket()
//      );
//
//      return this.loyaltyCardRepository.save(currentLoyaltyCard);
//   }

   public void deleteLoyaltyCardById(long id) {
      if (!this.loyaltyCardRepository.existsById(id))
         throw new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id));

      this.loyaltyCardRepository.deleteById(id);
   }

   //jqpl
   public Float getAverageSpentMoney() {
      return this.loyaltyCardRepository.getAverageSpentMoney();
   }
}
