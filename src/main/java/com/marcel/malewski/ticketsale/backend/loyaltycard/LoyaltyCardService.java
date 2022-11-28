package com.marcel.malewski.ticketsale.backend.loyaltycard;

import com.marcel.malewski.ticketsale.backend.loyaltycard.exceptions.LoyaltyCardNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCardConstants.LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE;

@Service
public class LoyaltyCardService {
   private final LoyaltyCardRepository loyaltyCardRepository;

   public LoyaltyCardService(LoyaltyCardRepository loyaltyCardRepository) {
      this.loyaltyCardRepository = loyaltyCardRepository;
   }

   public List<LoyaltyCard> getAllLoyaltyCards() {
      return this.loyaltyCardRepository.findAll();
   }

   public LoyaltyCard getLoyaltyCardById(long id) {
      return this.loyaltyCardRepository.findById(id).orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public LoyaltyCard postLoyaltyCard(LoyaltyCard loyaltyCard) {
      return this.loyaltyCardRepository.save(loyaltyCard);
   }

   public LoyaltyCard putLoyaltyCardById(long id, LoyaltyCard loyaltyCard) {
      if (!this.loyaltyCardRepository.existsById(id))
         throw new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id));

      loyaltyCard.setId(id);
      return this.loyaltyCardRepository.save(loyaltyCard);
   }

   public LoyaltyCard patchLoyaltyCardById(long id, LoyaltyCard loyaltyCard) {
      LoyaltyCard currentLoyaltyCard = this.loyaltyCardRepository.findById(id)
              .orElseThrow(() -> new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id)));

      currentLoyaltyCard.setMoneySpent(
              (loyaltyCard.getMoneySpent() != null) ? loyaltyCard.getMoneySpent() : currentLoyaltyCard.getMoneySpent()
      );
      currentLoyaltyCard.setNumberOfWatchedMovies(
              (loyaltyCard.getNumberOfWatchedMovies() != null) ? loyaltyCard.getNumberOfWatchedMovies() : currentLoyaltyCard.getNumberOfWatchedMovies()
      );
      currentLoyaltyCard.setDiscountOnTheNextTicket(
              (loyaltyCard.getDiscountOnTheNextTicket() != null) ? loyaltyCard.getDiscountOnTheNextTicket() : currentLoyaltyCard.getDiscountOnTheNextTicket()
      );

      return this.loyaltyCardRepository.save(currentLoyaltyCard);
   }

   public void deleteLoyaltyCardById(long id) {
      if (!this.loyaltyCardRepository.existsById(id))
         throw new LoyaltyCardNotFoundException(String.format(LOYALTY_CARD_BY_ID_NOT_FOUND_MESSAGE, id));

      this.loyaltyCardRepository.deleteById(id);
   }
}
