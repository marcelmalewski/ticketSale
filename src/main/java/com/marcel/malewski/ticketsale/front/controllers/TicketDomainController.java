package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.ticket.TicketService;
import com.marcel.malewski.ticketsale.backend.ticket.dto.TicketResponseDto;
import com.marcel.malewski.ticketsale.front.dto.TicketWithValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
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
      return "ticket/ticketHome";
   }

   @RequestMapping("/add")
   public String getTicketAdd(Model model) {
      TicketWithValidation ticketWithValidation = new TicketWithValidation();
      model.addAttribute("ticketWithValidation", ticketWithValidation);
      return "ticket/ticketAdd";
   }

   @PostMapping("/add/validate")
   public String processTicketPost(
           @Valid TicketWithValidation ticketWithValidation,
           Errors errors, Model model){
      model.addAttribute("ticketWithValidation", ticketWithValidation);
      if(errors.hasErrors()){
         return "ticket/ticketAdd";
      }
      this.ticketService.postTicket(ticketWithValidation);
      return "redirect:/front/v1/tickets/home";
   }

   @RequestMapping("/update/{id}")
   public String getTicketPut(@PathVariable(name = "id") long id, Model model) {
      TicketWithValidation ticketWithValidation = this.ticketService.getTicketWithValidationById(id);
      model.addAttribute("ticketWithValidation", ticketWithValidation);
      return "ticket/ticketUpdate";
   }

   @PostMapping("/update/validate")
   public String processLoyaltyCardPut(
           @Valid TicketWithValidation ticketWithValidation,
           Errors errors, Model model) {
      model.addAttribute("ticketWithValidation", ticketWithValidation);
      if (errors.hasErrors()) {
         return "ticket/ticketUpdate";
      }
      this.ticketService.putTicketById(ticketWithValidation.getId(), ticketWithValidation);

      return "redirect:/front/v1/ticket-buyers/home";
   }
}
