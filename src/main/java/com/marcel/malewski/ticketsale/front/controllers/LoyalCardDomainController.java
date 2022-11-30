package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCardService;
import com.marcel.malewski.ticketsale.front.dto.LoyaltyCardPostDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/front/v1/loyalty-cards")
public class LoyalCardDomainController {
   private final LoyaltyCardService loyaltyCardService;

   public LoyalCardDomainController(LoyaltyCardService loyaltyCardService) {
      this.loyaltyCardService = loyaltyCardService;
   }

   @GetMapping("/home")
   public String getLoyaltyCardHome(Model model) {
      LoyaltyCardPostDto loyaltyCardPostDto = new LoyaltyCardPostDto();
      model.addAttribute("loyaltyCardPostDto", loyaltyCardPostDto);
      return "loyaltyCardHome";
   }

   @GetMapping("/form")
   public String getLoyaltyCardForm(Model model) {
      LoyaltyCardPostDto loyaltyCardPostDto = new LoyaltyCardPostDto();
      model.addAttribute("loyaltyCardPostDto", loyaltyCardPostDto);
      return "loyaltyCardForm";
   }

   @PostMapping("/create")
   public String processLoyaltyCardPost(@Valid LoyaltyCardPostDto loyaltyCardPostDto, Errors errors, Model model) {
      model.addAttribute("loyaltyCardPostDto", loyaltyCardPostDto);
      if (errors.hasErrors()) {
         return "loyaltyCardForm";
      }
      System.out.println(loyaltyCardPostDto);
      this.loyaltyCardService.postLoyaltyCard(loyaltyCardPostDto.toLoyaltyCard());

      return "redirect:/front/v1/loyalty-cards/home";
   }
}
