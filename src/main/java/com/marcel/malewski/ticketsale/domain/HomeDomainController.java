package com.marcel.malewski.ticketsale.domain;

import com.marcel.malewski.ticketsale.loyaltycard.LoyaltyCardService;
import com.marcel.malewski.ticketsale.ticketbuyer.queryInterface.NumberOfTicketBuyersByAgeRange;
import com.marcel.malewski.ticketsale.ticketbuyer.queryInterface.TicketBuyerCountMoviesByAgeRange;
import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyerService;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/front/v1")
public class HomeDomainController {
   private final TicketBuyerService ticketBuyerService;
   private final LoyaltyCardService loyaltyCardService;

   public HomeDomainController(TicketBuyerService ticketBuyerService, LoyaltyCardService loyaltyCardService) {
      this.ticketBuyerService = ticketBuyerService;
      this.loyaltyCardService = loyaltyCardService;
   }

   @RequestMapping("/home")
   public String getHome() {
      return "home";
   }

   @RequestMapping("/jpql")
   public String getJpql(Model model) {
      List<TicketBuyerCountMoviesByAgeRange> ticketBuyerMovies = this.ticketBuyerService.getTicketBuyerMoviesByAgeRange();
      model.addAttribute("ticketBuyerMovies", ticketBuyerMovies);

      List<TicketBuyerResponseDto> ticketByersOrdered = this.ticketBuyerService.ticketBuyersOrderedByName();
      model.addAttribute("ticketByersOrdered", ticketByersOrdered);

      Float averageSpentMoney = this.loyaltyCardService.getAverageSpentMoney();
      model.addAttribute("averageSpentMoney", averageSpentMoney);

      List<NumberOfTicketBuyersByAgeRange> numberOfTicketBuyersByAgeRanges = this.ticketBuyerService.getNumberOfTicketBuyersByAgeRange();
      model.addAttribute("numberOfTicketBuyersByAgeRanges", numberOfTicketBuyersByAgeRanges);

      List<TicketBuyerResponseDto> ticketBuyersBeforeYear2000 = this.ticketBuyerService.ticketByersBeforeYear2000();
      model.addAttribute("ticketBuyersBeforeYear2000", ticketBuyersBeforeYear2000);

      return "advancedJPQL";
   }
}
