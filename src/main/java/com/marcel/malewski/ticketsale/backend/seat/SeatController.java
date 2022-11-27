package com.marcel.malewski.ticketsale.backend.seat;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/seats")
public class SeatController {
   private final SeatService seatService;
   
   public SeatController(SeatService seatService) {
      this.seatService = seatService;
   }

   @GetMapping
   @Operation(summary = "Get all seats")
   public ResponseEntity<List<Seat>> getAllSeats() {
      List<Seat> seats = this.seatService.getAllSeats();
      return new ResponseEntity<>(seats, HttpStatus.OK);
   }

   @GetMapping(path = "{id}")
   @Operation(summary = "Get seat by id")
   public ResponseEntity<Seat> getSeatById(@PathVariable("id") long id) {
      Seat seat = this.seatService.getSeatById(id);
      return new ResponseEntity<>(seat, HttpStatus.OK);
   }

   @PostMapping
   @Operation(summary = "Create new seat")
   public ResponseEntity<Seat> postSeat(@RequestBody Seat seat) {
      Seat createdSeat = this.seatService.postSeat(seat);
      return new ResponseEntity<>(createdSeat, HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update seat buyer by id")
   public ResponseEntity<Seat> updateSeat(@PathVariable("id") long id, @RequestBody Seat seat) {
      Seat updatedSeat = this.seatService.putSeatById(id, seat);
      return new ResponseEntity<>(updatedSeat, HttpStatus.OK);
   }

   @PatchMapping(path = "{id}")
   @Operation(summary = "Update seat partially by id")
   public ResponseEntity<Seat> patchTicketBuyer(@PathVariable("id") long id, @RequestBody Seat seat) {
      Seat patchedSeat = this.seatService.patchSeatById(id, seat);
      return new ResponseEntity<>(patchedSeat, HttpStatus.OK);
   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete seat by id")
   public ResponseEntity<Long> deleteSeatById(@PathVariable("id") long id) {
      this.seatService.deleteSeatById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
