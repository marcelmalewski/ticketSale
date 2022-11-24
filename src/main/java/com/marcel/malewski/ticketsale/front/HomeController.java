package com.marcel.malewski.ticketsale.front;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.ParseException;

@Controller
public class HomeController {
   @GetMapping("/home")
   public String home(Model model) throws ParseException {
      TicketBuyer ticketBuyer = new TicketBuyer(2, "Jan");
      model.addAttribute("ticketBuyer", ticketBuyer);
      return "home";
   }
}
