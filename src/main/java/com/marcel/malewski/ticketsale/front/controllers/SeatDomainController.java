package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.seat.SeatService;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.front.dto.SeatWithValidationDto;
import com.marcel.malewski.ticketsale.front.dto.TicketBuyerWithValidationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
      List<Seat> seats = this.seatService.getAllSeats();
      model.addAttribute("seats", seats);
      return "seat/seatHome";
   }

   @RequestMapping("/add")
   public String getTicketBuyerAdd(Model model) {
      SeatWithValidationDto seatWithValidationDto = new SeatWithValidationDto();
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      return "seat/seatAdd";
   }

   @PostMapping("/add/validate")
   public String processTicketBuyerPost(
           @Valid SeatWithValidationDto seatWithValidationDto,
           Errors errors, Model model){
      model.addAttribute("seatWithValidationDto", seatWithValidationDto);
      if(errors.hasErrors()){
         return "seat/seatAdd";
      }

      this.seatService.postSeat(seatWithValidationDto);
      return "redirect:/front/v1/seats/home";
   }
}
