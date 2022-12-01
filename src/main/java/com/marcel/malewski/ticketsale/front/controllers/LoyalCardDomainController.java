package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCard;
import com.marcel.malewski.ticketsale.backend.loyaltycard.LoyaltyCardService;
import com.marcel.malewski.ticketsale.front.dto.LoyaltyCardWithValidationDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/front/v1/loyalty-cards")
public class LoyalCardDomainController {
   private final LoyaltyCardService loyaltyCardService;

   public LoyalCardDomainController(LoyaltyCardService loyaltyCardService) {
      this.loyaltyCardService = loyaltyCardService;
   }

   @GetMapping("/home")
   public String getLoyaltyCardHome(Model model) {
      List<LoyaltyCard> loyaltyCards = this.loyaltyCardService.getAllLoyaltyCards();
      model.addAttribute("loyaltyCards", loyaltyCards);
      return "loyaltyCardHome";
   }

   @GetMapping("/add")
   public String getLoyaltyCardAdd(Model model) {
      LoyaltyCardWithValidationDto loyaltyCardWithValidationDto = new LoyaltyCardWithValidationDto();
      model.addAttribute("loyaltyCard", loyaltyCardWithValidationDto);
      return "loyaltyCardAdd";
   }

   @PostMapping("/add/validate")
//   @ResponseStatus(HttpStatus.CREATED)
   public String processLoyaltyCardPost(@Valid LoyaltyCardWithValidationDto loyaltyCardWithValidationDto, Errors errors, Model model) {
      model.addAttribute("loyaltyCard", loyaltyCardWithValidationDto);
      if (errors.hasErrors()) {
         return "loyaltyCardAdd";
      }
      LoyaltyCard loyaltyCard = loyaltyCardWithValidationDto.toLoyaltyCard();
      System.out.println(loyaltyCard);
      //this.loyaltyCardService.postLoyaltyCard();

      return "redirect:/front/v1/loyalty-cards/home";
   }

   @GetMapping("/update/{id}")
   public String getLoyaltyCardPut(@PathVariable(name = "id") long id, Model model) {
      LoyaltyCard loyaltyCardToUpdate = this.loyaltyCardService.getLoyaltyCardById(id);
      LoyaltyCardWithValidationDto loyaltyCardWithValidationDto = loyaltyCardToUpdate.toLoyaltyCardWithValidationDto();
      model.addAttribute("loyaltyCard", loyaltyCardWithValidationDto);
      return "loyaltyCardUpdate";
   }

   @PutMapping("/update/validate")
   public String processLoyaltyCardPut(@Valid LoyaltyCardWithValidationDto loyaltyCardWithValidationDto, Errors errors, Model model) {
      model.addAttribute("loyaltyCard", loyaltyCardWithValidationDto);
      if (errors.hasErrors()) {
         return "loyaltyCardUpdate";
      }
      this.loyaltyCardService.postLoyaltyCard(loyaltyCardWithValidationDto.toLoyaltyCard());

      return "redirect:/front/v1/loyalty-cards/home";
   }

   @DeleteMapping("/delete/{id}")
   public String processLoyaltyCardDelete(@PathVariable(name = "id") long id) {
      this.loyaltyCardService.deleteLoyaltyCardById(id);
      return "redirect:/front/v1/loyalty-cards/home";
   }
}
