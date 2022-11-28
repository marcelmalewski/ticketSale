package com.marcel.malewski.ticketsale.backend.loyaltycard;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/loyalty-cards")
public class LoyaltyCardController {
   private final LoyaltyCardService loyaltyCardService;

   public LoyaltyCardController(LoyaltyCardService loyaltyCardService) {
      this.loyaltyCardService = loyaltyCardService;
   }

   @GetMapping
   @Operation(summary = "Get all loyalty cards")
   public ResponseEntity<List<LoyaltyCard>> getAllLoyaltyCards() {
      List<LoyaltyCard> loyaltyCards = this.loyaltyCardService.getAllLoyaltyCards();
      return new ResponseEntity<>(loyaltyCards, HttpStatus.OK);
   }

   @GetMapping(path = "{id}")
   @Operation(summary = "Get loyalty card by id")
   public ResponseEntity<LoyaltyCard> getLoyaltyCardById(@PathVariable("id") long id) {
      LoyaltyCard loyaltyCard = this.loyaltyCardService.getLoyaltyCardById(id);
      return new ResponseEntity<>(loyaltyCard, HttpStatus.OK);
   }

   @PostMapping
   @Operation(summary = "Create new loyalty card")
   public ResponseEntity<LoyaltyCard> postLoyaltyCard(@Valid @RequestBody LoyaltyCard loyaltyCard) {
      LoyaltyCard createdLoyaltyCard = this.loyaltyCardService.postLoyaltyCard(loyaltyCard);
      return new ResponseEntity<>(createdLoyaltyCard, HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update loyalty card by id")
   public ResponseEntity<LoyaltyCard> updateLoyaltyCard(@PathVariable("id") long id, @Valid @RequestBody LoyaltyCard loyaltyCard) {
      LoyaltyCard updatedLoyaltyCard = this.loyaltyCardService.putLoyaltyCardById(id, loyaltyCard);
      return new ResponseEntity<>(updatedLoyaltyCard, HttpStatus.OK);
   }

   @PatchMapping(path = "{id}")
   @Operation(summary = "Update loyalty card partially by id")
   public ResponseEntity<LoyaltyCard> patchLoyaltyCard(@PathVariable("id") long id, @Valid @RequestBody LoyaltyCard loyaltyCarduyer) {
      LoyaltyCard patchedLoyaltyCard = this.loyaltyCardService.patchLoyaltyCardById(id, loyaltyCarduyer);
      return new ResponseEntity<>(patchedLoyaltyCard, HttpStatus.OK);
   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete loyalty card by id")
   public ResponseEntity<Long> deleteLoyaltyCardById(@PathVariable("id") long id) {
      this.loyaltyCardService.deleteLoyaltyCardById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);

   }
}
