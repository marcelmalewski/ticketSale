package com.marcel.malewski.ticketsale.backend.ticket.dto;

import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class TicketResponseDto {
   private Long id;
   private String movieName;
   private ZonedDateTime showDate;
   private Integer hallNumber;
   private List<Long> ticketBuyers;
   private List<Long> seats;
}
