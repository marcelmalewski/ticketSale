package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCardService;
import com.marcel.malewski.ticketsale.front.dto.LoyaltyCardWithValidationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/front/v1/loyalty-cards")
public class LoyaltyCardDomainController {
   private final LoyaltyCardService loyaltyCardService;

   public LoyaltyCardDomainController(LoyaltyCardService loyaltyCardService) {
      this.loyaltyCardService = loyaltyCardService;
   }

   @RequestMapping("/home")
   public String getLoyaltyCardHome(Model model) {
      List<LoyaltyCard> loyaltyCards = this.loyaltyCardService.getAllLoyaltyCards();
      model.addAttribute("loyaltyCards", loyaltyCards);
      return "loyaltyCard/loyaltyCardHome";
   }

   @RequestMapping("/add")
   public String getLoyaltyCardAdd(Model model) {
      LoyaltyCardWithValidationDto loyaltyCardWithValidationDto = new LoyaltyCardWithValidationDto();
      model.addAttribute("loyaltyCardWithValidationDto", loyaltyCardWithValidationDto);
      return "loyaltyCard/loyaltyCardAdd";
   }

   @PostMapping("/add/validate")
   public String processLoyaltyCardPost(
           @Valid LoyaltyCardWithValidationDto loyaltyCardWithValidationDto,
           Errors errors, Model model) {
      model.addAttribute("loyaltyCardWithValidationDto", loyaltyCardWithValidationDto);
      if (errors.hasErrors()) {
         return "loyaltyCard/loyaltyCardAdd";
      }
      LoyaltyCard loyaltyCard = loyaltyCardWithValidationDto.toLoyaltyCard();
      this.loyaltyCardService.postLoyaltyCard(loyaltyCard);

      return "redirect:/front/v1/loyalty-cards/home";
   }

   @RequestMapping("/update/{id}")
   public String getLoyaltyCardPut(@PathVariable(name = "id") long id, Model model) {
      LoyaltyCard loyaltyCardToUpdate = this.loyaltyCardService.getLoyaltyCardById(id);
      LoyaltyCardWithValidationDto loyaltyCardWithValidationDto = loyaltyCardToUpdate.toLoyaltyCardWithValidationDto();
      model.addAttribute("loyaltyCardWithValidationDto", loyaltyCardWithValidationDto);
      return "loyaltyCard/loyaltyCardUpdate";
   }

   @PostMapping("/update/validate")
   public String processLoyaltyCardPut(
           @Valid LoyaltyCardWithValidationDto loyaltyCardWithValidationDto,
           Errors errors, Model model) {
      model.addAttribute("loyaltyCardWithValidationDto", loyaltyCardWithValidationDto);
      if (errors.hasErrors()) {
         return "loyaltyCard/loyaltyCardUpdate";
      }
      LoyaltyCard loyaltyCard = loyaltyCardWithValidationDto.toLoyaltyCard();
      this.loyaltyCardService.putLoyaltyCardById(loyaltyCard.getId(), loyaltyCard);

      return "redirect:/front/v1/loyalty-cards/home";
   }

   @GetMapping("/delete/{id}")
   public String processLoyaltyCardDelete(@PathVariable(name = "id") long id) {
      this.loyaltyCardService.deleteLoyaltyCardById(id);
      return "redirect:/front/v1/loyalty-cards/home";
   }
}
