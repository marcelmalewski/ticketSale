package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.seat.dto.SeatResponseDto;
import com.marcel.malewski.ticketsale.front.dto.SeatWithValidationDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
   public ResponseEntity<List<SeatResponseDto>> getAllSeats() {
      List<SeatResponseDto> seatsResponseDto = this.seatService.getAllSeats();
      return new ResponseEntity<>(seatsResponseDto, HttpStatus.OK);
   }

//   @GetMapping(path = "{id}")
//   @Operation(summary = "Get seat by id")
//   public ResponseEntity<Seat> getSeatById(@PathVariable("id") long id) {
//      Seat seat = this.seatService.getSeatById(id);
//      return new ResponseEntity<>(seat, HttpStatus.OK);
//   }

   @PostMapping
   @Operation(summary = "Create new seat")
   public ResponseEntity<String> postSeat(@Valid @RequestBody SeatWithValidationDto seatWithValidationDto) {
      this.seatService.postSeat(seatWithValidationDto);
      return new ResponseEntity<>("created", HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update seat by id")
   public ResponseEntity<String> updateSeat(@PathVariable("id") long id, @Valid @RequestBody SeatWithValidationDto seat) {
      this.seatService.putSeatById(id, seat);
      return new ResponseEntity<>("updated", HttpStatus.OK);
   }

//   @PatchMapping(path = "{id}")
//   @Operation(summary = "Update seat partially by id")
//   public ResponseEntity<Seat> patchTicketBuyer(@PathVariable("id") long id, @Valid @RequestBody Seat seat) {
//      Seat patchedSeat = this.seatService.patchSeatById(id, seat);
//      return new ResponseEntity<>(patchedSeat, HttpStatus.OK);
//   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete seat by id")
   public ResponseEntity<Long> deleteSeatById(@PathVariable("id") long id) {
      this.seatService.deleteSeatById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
