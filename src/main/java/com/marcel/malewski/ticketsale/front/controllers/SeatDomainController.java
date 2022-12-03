package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.seat.SeatService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
