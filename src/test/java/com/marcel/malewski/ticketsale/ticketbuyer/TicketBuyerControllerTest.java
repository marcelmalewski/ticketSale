package com.marcel.malewski.ticketsale.ticketbuyer;

import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerResponseDto;
import com.marcel.malewski.ticketsale.ticketbuyer.dto.TicketBuyerWithValidationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TicketBuyerControllerTest {

   @InjectMocks
   TicketBuyerController ticketBuyerController;

   @Mock
   TicketBuyerService ticketBuyerService;

   @Test
   void getAllTicketBuyers() {
      TicketBuyerResponseDto ticketBuyer1 = new TicketBuyerResponseDto(
              1L,
              "Marcel",
              "Malewski",
              "1234",
              new Date(),
              "email",
              "ADULT"
      );
      TicketBuyerResponseDto ticketBuyer2 = new TicketBuyerResponseDto(
              2L,
              "Marcel",
              "Malewski",
              "1234",
              new Date(),
              "email",
              "ADULT"
      );

      List<TicketBuyerResponseDto> ticketBuyers = List.of(ticketBuyer1, ticketBuyer2);

      //mockuje serwis
      when(ticketBuyerService.getAllTicketBuyers()).thenReturn(ticketBuyers);

      //wywoluje metode kontrolera
      ResponseEntity<List<TicketBuyerResponseDto>> allTicketBuyers = ticketBuyerController.getAllTicketBuyers();

      //sprawdzam czy body jest takie jak mockowane
      assertEquals(ticketBuyers, allTicketBuyers.getBody());
   }

   @Test
   void postTicketBuyer() {
      TicketBuyerWithValidationDto ticketBuyer = new TicketBuyerWithValidationDto(
              "Marcel",
              "Malewski",
              "1234MMmasd!!",
              new Date(),
              "email",
              "ADULT",
              1L
      );

      doNothing().when(ticketBuyerService).postTicketBuyer(ticketBuyer);

      ResponseEntity<String> responseEntity = ticketBuyerController.postTicketBuyer(ticketBuyer);

      assertEquals("created", responseEntity.getBody());
   }
}
