package com.marcel.malewski.ticketsale.domain;

import com.marcel.malewski.ticketsale.ticket.TicketService;
import com.marcel.malewski.ticketsale.ticket.dto.TicketResponseDto;
import com.marcel.malewski.ticketsale.ticket.dto.TicketWithValidation;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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

   @PutMapping("/update/validate/{id}")
   public String processTicketPut(
           @PathVariable(name = "id") long id,
           @Valid TicketWithValidation ticketWithValidation,
           Errors errors, Model model) {
      model.addAttribute("ticketWithValidation", ticketWithValidation);
      model.addAttribute("id", id);

      if (errors.hasErrors()) {
         return "ticket/ticketUpdate";
      }
      this.ticketService.putTicketById(id, ticketWithValidation);

      return "redirect:/front/v1/tickets/home";
   }

   @DeleteMapping("/delete/{id}")
   public String processTicketDelete(@PathVariable(name = "id") long id) {
      this.ticketService.deleteTicketById(id);
      return "redirect:/front/v1/tickets/home";
   }
}
