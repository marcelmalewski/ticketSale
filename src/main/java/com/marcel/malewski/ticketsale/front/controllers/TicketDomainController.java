package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.ticket.TicketService;
import com.marcel.malewski.ticketsale.backend.ticket.dto.TicketResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/front/v1/tickets")
public class TicketDomainController {
   private final TicketService ticketService;

   public TicketDomainController(TicketService ticketService) {
      this.ticketService = ticketService;
   }

   @RequestMapping("/home")
   public String getTicketHome(Model model) {
      List<TicketResponseDto> ticketsResponseDto = this.ticketService.getAllTickets();
      model.addAttribute("ticketsResponseDto", ticketsResponseDto);
      return "loyaltyCard/loyaltyCardHome";
   }
}
