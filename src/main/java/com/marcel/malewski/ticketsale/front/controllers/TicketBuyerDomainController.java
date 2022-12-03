package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyerService;
import com.marcel.malewski.ticketsale.front.dto.TicketBuyerWithValidationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/front/v1/ticket-buyers")
public class TicketBuyerDomainController {
   private final TicketBuyerService ticketBuyerService;

   public TicketBuyerDomainController(TicketBuyerService ticketBuyerService) {
      this.ticketBuyerService = ticketBuyerService;
   }

   @RequestMapping("/home")
   public String getTicketBuyerHome(Model model) {
      List<TicketBuyer> ticketBuyers = this.ticketBuyerService.getAllTicketBuyers();
      model.addAttribute("ticketBuyers", ticketBuyers);
      return "ticketBuyer/ticketBuyerHome";
   }

   @RequestMapping("/add")
   public String getTicketBuyerAdd(Model model) {
      TicketBuyerWithValidationDto ticketBuyerWithValidationDto = new TicketBuyerWithValidationDto();
      model.addAttribute("ticketBuyerWithValidationDto", ticketBuyerWithValidationDto);
      return "ticketBuyer/ticketBuyerAdd";
   }

   @PostMapping("/add/validate")
   public String processTicketBuyerPost(
           @Valid TicketBuyerWithValidationDto ticketBuyerWithValidationDto,
           Errors errors, Model model){
      model.addAttribute("ticketBuyerWithValidationDto", ticketBuyerWithValidationDto);
      if(errors.hasErrors()){
         return "ticketBuyer/ticketBuyerAdd";
      }
      TicketBuyer ticketBuyer = ticketBuyerWithValidationDto.toTicketBuyer();
      this.ticketBuyerService.postTicketBuyer(ticketBuyer);
      return "redirect:/front/v1/ticket-buyers/home";
   }
}
