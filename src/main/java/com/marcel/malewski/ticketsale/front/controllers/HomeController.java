package com.marcel.malewski.ticketsale.front.controllers;

import com.marcel.malewski.ticketsale.front.TicketBuyer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
public class HomeController {
//   @GetMapping("/front/home")
//   public String home(Model model) throws ParseException {
//      TicketBuyer ticketBuyer = new TicketBuyer(2, "Jan");
//      model.addAttribute("ticketBuyer", ticketBuyer);
//      return "home";
//   }
   @RequestMapping({"/front", "/front/", "/front/home"})
   public String getHomePage() {
      return "home2";
   }
}
