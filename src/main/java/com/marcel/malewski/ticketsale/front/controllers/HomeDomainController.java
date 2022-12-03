package com.marcel.malewski.ticketsale.front.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/front/v1")
public class HomeDomainController {
   @RequestMapping("/home")
   public String getHome() {
      return "home";
   }
}
