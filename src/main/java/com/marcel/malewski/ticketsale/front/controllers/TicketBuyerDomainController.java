package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyerService;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.dto.TicketBuyerResponseDto;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.dto.TicketBuyerWithValidationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
      List<TicketBuyerResponseDto> ticketBuyersResponseDto = this.ticketBuyerService.getAllTicketBuyers();
      model.addAttribute("ticketBuyersResponseDto", ticketBuyersResponseDto);
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
      this.ticketBuyerService.postTicketBuyer(ticketBuyerWithValidationDto);
      return "redirect:/front/v1/ticket-buyers/home";
   }

   @RequestMapping("/update/{id}")
   public String getTicketBuyerPut(@PathVariable(name = "id") long id, Model model) {
      TicketBuyerWithValidationDto ticketBuyerWithValidationDto = this.ticketBuyerService.getTicketBuyerValidationById(id);
      model.addAttribute("ticketBuyerWithValidationDto", ticketBuyerWithValidationDto);
      return "ticketBuyer/ticketBuyerUpdate";
   }

   @PutMapping("/update/validate/{id}")
   public String processTicketBuyerPut(
           @PathVariable(name = "id") long id,
           @Valid TicketBuyerWithValidationDto ticketBuyerWithValidationDto,
           Errors errors, Model model) {
      model.addAttribute("ticketBuyerWithValidationDto", ticketBuyerWithValidationDto);
      model.addAttribute("id", id);

      if (errors.hasErrors()) {
         return "ticketBuyer/ticketBuyerUpdate";
      }
      this.ticketBuyerService.putTicketBuyerById(id, ticketBuyerWithValidationDto);

      return "redirect:/front/v1/ticket-buyers/home";
   }

   @DeleteMapping("/delete/{id}")
   public String processTicketBuyerDelete(@PathVariable(name = "id") long id) {
      this.ticketBuyerService.deleteTicketBuyerById(id);
      return "redirect:/front/v1/ticket-buyers/home";
   }
}
