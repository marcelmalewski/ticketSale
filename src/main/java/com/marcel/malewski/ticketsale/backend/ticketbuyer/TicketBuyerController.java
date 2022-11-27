package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ticket-buyers")
public class TicketBuyerController {
   private final TicketBuyerService ticketBuyerService;

   public TicketBuyerController(TicketBuyerService ticketBuyerService) {
      this.ticketBuyerService = ticketBuyerService;
   }

   @GetMapping
   @Operation(summary = "Get all ticket buyers")
   public ResponseEntity<List<TicketBuyer>> getAllTicketBuyers() {
      List<TicketBuyer> ticketBuyers = this.ticketBuyerService.getAllTicketBuyers();
      return new ResponseEntity<>(ticketBuyers, HttpStatus.OK);
   }

   @GetMapping(path = "{id}")
   @Operation(summary = "Get ticket buyer by id")
   public ResponseEntity<TicketBuyer> getTicketBuyerById(@PathVariable("id") long id) {
      TicketBuyer ticketBuyer = this.ticketBuyerService.getTicketBuyerById(id);
      return new ResponseEntity<>(ticketBuyer, HttpStatus.OK);
   }

   @PostMapping
   @Operation(summary = "Create new ticket buyer")
   public ResponseEntity<TicketBuyer> postTicketBuyer(@RequestBody TicketBuyer ticketBuyer) {
      TicketBuyer createdTicketBuyer = this.ticketBuyerService.postTicketBuyer(ticketBuyer);
      return new ResponseEntity<>(createdTicketBuyer, HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update ticket buyer by id")
   public ResponseEntity<TicketBuyer> updateTicketBuyer(@PathVariable("id") long id, @RequestBody TicketBuyer ticketBuyer) {
      TicketBuyer updatedTicketBuyer = this.ticketBuyerService.putTicketBuyerById(id, ticketBuyer);
      return new ResponseEntity<>(updatedTicketBuyer, HttpStatus.OK);
   }

   @PatchMapping(path = "{id}")
   @Operation(summary = "Update ticket buyer partially by id")
   public ResponseEntity<TicketBuyer> patchTicketBuyer(@PathVariable("id") long id, @RequestBody TicketBuyer ticketBuyer) {
      TicketBuyer patchedTicketBuyer = this.ticketBuyerService.patchTicketBuyerById(id, ticketBuyer);
      return new ResponseEntity<>(patchedTicketBuyer, HttpStatus.OK);
   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete ticket buyer by id")
   public ResponseEntity<Long> deleteTickerBuyer(@PathVariable("id") long id) {
      this.ticketBuyerService.deleteTicketBuyerById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
