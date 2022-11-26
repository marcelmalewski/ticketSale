package com.marcel.malewski.ticketsale.backend.loyaltycard;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
   public ResponseEntity<List<LoyaltyCard>> getAllClients() {
      List<LoyaltyCard> ticketBuyers = this.loyaltyCardService.getAllLoyaltyCards();
      return new ResponseEntity<>(ticketBuyers, HttpStatus.OK);
   }

   @GetMapping(path = "{id}")
   @Operation(summary = "Get loyalty card by id")
   public ResponseEntity<LoyaltyCard> getClientById(@PathVariable("id") long id) {
      LoyaltyCard ticketBuyer = this.loyaltyCardService.getLoyaltyCardById(id);
      return new ResponseEntity<>(ticketBuyer, HttpStatus.OK);
   }

   @PostMapping
   @Operation(summary = "Create new loyalty card")
   public ResponseEntity<LoyaltyCard> postClient(@RequestBody LoyaltyCard ticketBuyer) {
      LoyaltyCard createdTicketBuyer = this.loyaltyCardService.postLoyaltyCard(ticketBuyer);
      return new ResponseEntity<>(createdTicketBuyer, HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update loyalty card by id")
   public ResponseEntity<LoyaltyCard> updateClient(@PathVariable("id") long id, @RequestBody LoyaltyCard ticketBuyer) {
      LoyaltyCard updatedTicketBuyer = this.loyaltyCardService.putLoyaltyCardById(id, ticketBuyer);
      return new ResponseEntity<>(updatedTicketBuyer, HttpStatus.OK);
   }

   @PatchMapping(path = "{id}")
   @Operation(summary = "Update loyalty card partially by id")
   public ResponseEntity<LoyaltyCard> patchClient(@PathVariable("id") long id, @RequestBody LoyaltyCard ticketBuyer) {
      LoyaltyCard patchedTicketBuyer = this.loyaltyCardService.patchLoyaltyCardById(id, ticketBuyer);
      return new ResponseEntity<>(patchedTicketBuyer, HttpStatus.OK);
   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete loyalty card by id")
   public ResponseEntity<Long> deleteClient(@PathVariable("id") long id) {
      this.loyaltyCardService.deleteLoyaltyCardById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);

   }
}
