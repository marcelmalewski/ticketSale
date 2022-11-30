package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.front.domain.TicketBuyerDomain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/front/v1/ticket-buyers")
public class TicketBuyerDomainController {
   @GetMapping("/home")
   public String getTicketBuyerPage(Model model) {
      TicketBuyerDomain ticketBuyerDomain = new TicketBuyerDomain();
      model.addAttribute("ticketBuyerDomain", ticketBuyerDomain);
      return "ticketBuyerHome";
   }

   @GetMapping("/form")
   public String getTicketBuyerForm(Model model) {
      TicketBuyerDomain ticketBuyerDomain = new TicketBuyerDomain();
      model.addAttribute("ticketBuyerDomain", ticketBuyerDomain);
      return "ticketBuyerForm";
   }

   @PostMapping("/create")
   public String processTicketBuyerPost(@Valid TicketBuyerDomain ticketBuyerDomain, Errors errors, Model model){
      model.addAttribute("ticketBuyerDomain", ticketBuyerDomain);
      if(errors.hasErrors()){
         return "ticketBuyerForm";
      }
      System.out.println(ticketBuyerDomain);
      return "redirect:/";
   }
}
