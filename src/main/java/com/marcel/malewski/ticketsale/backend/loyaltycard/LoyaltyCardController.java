package com.marcel.malewski.ticketsale.backend.loyaltycard;

import com.marcel.malewski.ticketsale.backend.loyaltycard.dto.LoyaltyCardResponseDto;
import com.marcel.malewski.ticketsale.backend.loyaltycard.dto.LoyaltyCardWithValidationDto;
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
   public ResponseEntity<List<LoyaltyCardResponseDto>> getAllLoyaltyCards() {
      List<LoyaltyCardResponseDto> loyaltyCardsResponseDto = this.loyaltyCardService.getAllLoyaltyCards();
      return new ResponseEntity<>(loyaltyCardsResponseDto, HttpStatus.OK);
   }

//   @GetMapping(path = "{id}")
//   @Operation(summary = "Get loyalty card by id")
//   public ResponseEntity<LoyaltyCard> getLoyaltyCardById(@PathVariable("id") long id) {
//      LoyaltyCard loyaltyCard = this.loyaltyCardService.getLoyaltyCardById(id);
//      return new ResponseEntity<>(loyaltyCard, HttpStatus.OK);
//   }

   @PostMapping
   @Operation(summary = "Create new loyalty card")
   public ResponseEntity<String> postLoyaltyCard(@Valid @RequestBody LoyaltyCardWithValidationDto loyaltyCardWithValidationDto) {
      this.loyaltyCardService.postLoyaltyCard(loyaltyCardWithValidationDto);
      return new ResponseEntity<>("Created", HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update loyalty card by id")
   public ResponseEntity<String> updateLoyaltyCard(@PathVariable("id") long id, @Valid @RequestBody LoyaltyCardWithValidationDto loyaltyCardWithValidationDto) {
      this.loyaltyCardService.putLoyaltyCardById(id, loyaltyCardWithValidationDto);
      return new ResponseEntity<>("Updated", HttpStatus.OK);
   }

//   @PatchMapping(path = "{id}")
//   @Operation(summary = "Update loyalty card partially by id")
//   public ResponseEntity<LoyaltyCard> patchLoyaltyCard(@PathVariable("id") long id, @Valid @RequestBody LoyaltyCard loyaltyCarduyer) {
//      LoyaltyCard patchedLoyaltyCard = this.loyaltyCardService.patchLoyaltyCardById(id, loyaltyCarduyer);
//      return new ResponseEntity<>(patchedLoyaltyCard, HttpStatus.OK);
//   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete loyalty card by id")
   public ResponseEntity<Long> deleteLoyaltyCardById(@PathVariable("id") long id) {
      this.loyaltyCardService.deleteLoyaltyCardById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);

   }
}
