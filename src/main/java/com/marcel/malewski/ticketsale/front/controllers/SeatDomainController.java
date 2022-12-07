package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.seat.SeatService;
import com.marcel.malewski.ticketsale.backend.seat.dto.SeatResponseDto;
import com.marcel.malewski.ticketsale.backend.seat.dto.SeatWithValidationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/front/v1/seats")
public class SeatDomainController {
   private final SeatService seatService;

   public SeatDomainController(SeatService seatService) {
      this.seatService = seatService;
   }

   @RequestMapping("/home")
   public String getSeatHome(Model model) {
      List<SeatResponseDto> seatsResponseDto = this.seatService.getAllSeats();
      model.addAttribute("seatsResponseDto", seatsResponseDto);
      return "seat/seatHome";
   }

   @RequestMapping("/add")
   public String getSeatAdd(Model model) {
      SeatWithValidationDto seatWithValidationDto = new SeatWithValidationDto();
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      return "seat/seatAdd";
   }

   @PostMapping("/add/validate")
   public String processSeatPost(
           @Valid SeatWithValidationDto seatWithValidationDto,
           Errors errors, Model model){
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      if(errors.hasErrors()){
         return "seat/seatAdd";
      }

      this.seatService.postSeat(seatWithValidationDto);
      return "redirect:/front/v1/seats/home";
   }

   @RequestMapping("/update/{id}")
   public String getSeatPut(@PathVariable(name = "id") long id, Model model) {
      SeatWithValidationDto seatWithValidationDto = this.seatService.getSeatWithValidationDtoById(id);
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      model.addAttribute("id", id);
      return "seat/seatUpdate";
   }

   @PutMapping("/update/validate/{id}")
   public String processSeatPut(
           @PathVariable(name = "id") long id,
           @Valid SeatWithValidationDto seatWithValidationDto,
           Errors errors, Model model) {
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      model.addAttribute("id", id);

      if (errors.hasErrors()) {
         return "seat/seatUpdate";
      }
      this.seatService.putSeatById(id, seatWithValidationDto);

      return "redirect:/front/v1/seats/home";
   }

   @DeleteMapping("/delete/{id}")
   public String processSeatDelete(@PathVariable(name = "id") long id) {
      this.seatService.deleteSeatById(id);
      return "redirect:/front/v1/seats/home";
   }
}
