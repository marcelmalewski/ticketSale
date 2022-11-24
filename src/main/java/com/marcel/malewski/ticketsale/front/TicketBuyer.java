package com.marcel.malewski.ticketsale.front;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TicketBuyer {
   private Integer id;
   private String name;

   public TicketBuyer(Integer id, String name) {
      this.id = id;
      this.name = name;
   }
}
