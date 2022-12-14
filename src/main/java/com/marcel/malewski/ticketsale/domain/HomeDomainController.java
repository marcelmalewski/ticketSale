package com.marcel.malewski.ticketsale.domain;

import com.marcel.malewski.ticketsale.jpql.JpqlService;
import com.marcel.malewski.ticketsale.loyaltycard.LoyaltyCardService;
import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyerService;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/front/v1")
public class HomeDomainController {
   private final JpqlService jpqlService;
   private final TicketBuyerService ticketBuyerService;
   private final LoyaltyCardService loyaltyCardService;

   public HomeDomainController(JpqlService jpqlService, TicketBuyerService ticketBuyerService, LoyaltyCardService loyaltyCardService) {
      this.jpqlService = jpqlService;
      this.ticketBuyerService = ticketBuyerService;
      this.loyaltyCardService = loyaltyCardService;
   }

   @RequestMapping("/home")
   public String getHome() {
      return "home";
   }

   @RequestMapping("/jpql")
   public String getJpql(Model model) {
      //wszyustkie filmy na ktorych byl ticket buyer to jest GROUP BY
     // List<TicketBuyerResponseDto> ticketBuyers = this.ticketBuyerService.getAllTicketBuyers();
//      model.addAttribute("ticketBuyers", ticketBuyers);

      //TicketBuyer ticketBuyerMovies = this.ticketBuyerService.getTicketBuyerMoviesById(1L, "Avatar");
//      model.addAttribute("ticketBuyerMovies", ticketBuyerMovies);
//      System.out.println(ticketBuyerMovies.getTickets());
//      System.out.println("yes");
      List<TicketBuyerResponseDto> ticketByersOrdered = this.ticketBuyerService.ticketBuyersOrderedByName();
      System.out.println(ticketByersOrdered);
      model.addAttribute("ticketByersOrdered", ticketByersOrdered);

      Float averageSpentMoney = this.loyaltyCardService.getAverageSpentMoney();
      model.addAttribute("averageSpentMoney", averageSpentMoney);

      return "advancedJPQL";
   }
}
