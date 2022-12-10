package com.marcel.malewski.ticketsale.domain;

import com.marcel.malewski.ticketsale.jpql.JpqlService;
import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.ticketbuyer.TicketBuyerService;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerResponseDto;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerWithMovies;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(path = "/front/v1")
public class HomeDomainController {
   private final JpqlService jpqlService;
   private final TicketBuyerService ticketBuyerService;

   public HomeDomainController(JpqlService jpqlService, TicketBuyerService ticketBuyerService) {
      this.jpqlService = jpqlService;
      this.ticketBuyerService = ticketBuyerService;
   }

   @RequestMapping("/home")
   public String getHome() {
      return "home";
   }

   @RequestMapping("/jpql")
   public String getJpql(Model model) {
      //wszyustkie filmy na ktorych byl ticket buyer to jest GROUP BY
      List<TicketBuyerResponseDto> ticketBuyers = this.ticketBuyerService.getAllTicketBuyers();
      model.addAttribute("ticketBuyers", ticketBuyers);

      TicketBuyer ticketBuyerMovies = this.ticketBuyerService.getTicketBuyerMoviesById(1L, "Avatar");
//      model.addAttribute("ticketBuyerMovies", ticketBuyerMovies);
      System.out.println(ticketBuyerMovies.getTickets());
      System.out.println("yes");


      return "advancedJPQL";
   }
}
