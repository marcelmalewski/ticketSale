package com.marcel.malewski.ticketsale.backend.cinemahall;

import com.marcel.malewski.ticketsale.backend.cinemahall.dto.CinemaHallResponseDto;
import com.marcel.malewski.ticketsale.backend.cinemahall.dto.CinemaHallWithValidationDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/cinema-halls")
public class CinemaHallController {
   private final CinemaHallService cinemaHallService;
   
   public CinemaHallController(CinemaHallService cinemaHallService) {
      this.cinemaHallService = cinemaHallService;
   }

   @GetMapping
   @Operation(summary = "Get all cinema halls")
   public ResponseEntity<List<CinemaHallResponseDto>> getAllCinemaHalls() {
      List<CinemaHallResponseDto> cinemaHalls = this.cinemaHallService.getAllCinemaHalls();
      return new ResponseEntity<>(cinemaHalls, HttpStatus.OK);
   }

//   @GetMapping(path = "{id}")
//   @Operation(summary = "Get cinema hall by id")
//   public ResponseEntity<CinemaHall> getCinemaHallById(@PathVariable("id") long id) {
//      CinemaHall cinemaHall = this.cinemaHallService.getCinemaHallById(id);
//      return new ResponseEntity<>(cinemaHall, HttpStatus.OK);
//   }

   @PostMapping
   @Operation(summary = "Create new cinema hall")
   public ResponseEntity<String> postCinemaHall(@Valid @RequestBody CinemaHallWithValidationDto cinemaHallWithValidationDto) {
      this.cinemaHallService.postCinemaHall(cinemaHallWithValidationDto);
      return new ResponseEntity<>("created", HttpStatus.CREATED);
   }

   @PutMapping(path = "{id}")
   @Operation(summary = "Update cinema hall by id")
   public ResponseEntity<String> updateCinemaHallById(@PathVariable("id") long id, @Valid @RequestBody CinemaHallWithValidationDto cinemaHallWithValidationDto) {
      this.cinemaHallService.putCinemaHallById(id, cinemaHallWithValidationDto);
      return new ResponseEntity<>("updated", HttpStatus.OK);
   }

//   @PatchMapping(path = "{id}")
//   @Operation(summary = "Update cinema hall partially by id")
//   public ResponseEntity<CinemaHall> patchCinemaHallById(@PathVariable("id") long id, @Valid @RequestBody CinemaHall cinemaHall) {
//      CinemaHall patchedCinemaHall = this.cinemaHallService.patchCinemaHallById(id, cinemaHall);
//      return new ResponseEntity<>(patchedCinemaHall, HttpStatus.OK);
//   }

   @DeleteMapping(path = "{id}")
   @Operation(summary = "Delete cinema hall by id")
   public ResponseEntity<Long> deleteCinemaHallById(@PathVariable("id") long id) {
      this.cinemaHallService.deleteCinemaHallById(id);
      return new ResponseEntity<>(id, HttpStatus.OK);
   }
}
