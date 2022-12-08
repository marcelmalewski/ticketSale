package com.marcel.malewski.ticketsale.ticketbuyer;

import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerResponseDto;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerWithValidationDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
   public ResponseEntity<List<TicketBuyerResponseDto>> getAllTicketBuyers() {
      List<TicketBuyerResponseDto> ticketBuyersResponseDto = this.ticketBuyerService.getAllTicketBuyers();
      return new ResponseEntity<>(ticketBuyersResponseDto, HttpStatus.OK);
   }

//   @GetMapping(path = "{id}")
//   @Operation(summary = "Get ticket buyer by id")
//   public ResponseEntity<TicketBuyer> getTicketBuyerById(@PathVariable("id") long id) {
//      TicketBuyer ticketBuyer = this.ticketBuyerService.getTicketBuyerById(id);
//      return new ResponseEntity<>(ticketBuyer, HttpStatus.OK);
//   }

   @PostMapping
   @Operation(summary = "Create new ticket buyer")
   public ResponseEntity<String> postTicketBuyer(@Valid @RequestBody TicketBuyerWithValidationDto ticketBuyerWithValidationDto) {
      this.ticketBuyerService.postTicketBuyer(ticketBuyerWithValidationDto);
      return new ResponseEntity<>("created", HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update ticket buyer by id")
   public ResponseEntity<String> updateTicketBuyer(@PathVariable("id") long id, @Valid @RequestBody TicketBuyerWithValidationDto ticketBuyerWithValidationDto) {
      this.ticketBuyerService.putTicketBuyerById(id, ticketBuyerWithValidationDto);
      return new ResponseEntity<>("updated", HttpStatus.OK);
   }

//   @PatchMapping(path = "{id}")
//   @Operation(summary = "Update ticket buyer partially by id")
//   public ResponseEntity<TicketBuyer> patchTicketBuyer(@PathVariable("id") long id, @Valid @RequestBody TicketBuyer ticketBuyer) {
//      TicketBuyer patchedTicketBuyer = this.ticketBuyerService.patchTicketBuyerById(id, ticketBuyer);
//      return new ResponseEntity<>(patchedTicketBuyer, HttpStatus.OK);
//   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete ticket buyer by id")
   public ResponseEntity<Long> deleteTickerBuyerById(@PathVariable("id") long id) {
      this.ticketBuyerService.deleteTicketBuyerById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
