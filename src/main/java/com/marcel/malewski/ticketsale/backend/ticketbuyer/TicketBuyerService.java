package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import com.marcel.malewski.ticketsale.backend.ticketbuyer.exceptions.TicketBuyerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyerConstants.TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE;

@Service
public class TicketBuyerService {
   private final TicketBuyerRepository ticketBuyerRepository;

   public TicketBuyerService(TicketBuyerRepository ticketBuyerRepository) {
      this.ticketBuyerRepository = ticketBuyerRepository;
   }

   public List<TicketBuyer> getAllTicketBuyers() {
      return this.ticketBuyerRepository.findAll();
   }

   public TicketBuyer getTicketBuyerById(long id) {
      return this.ticketBuyerRepository.findById(id).orElseThrow(() -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public TicketBuyer postTicketBuyer(TicketBuyer ticketBuyer) {
      //TODO zweryfikowac
      return this.ticketBuyerRepository.save(ticketBuyer);
   }

   public long putClient(long id, TicketBuyer ticketBuyer) {
      this.ticketBuyerRepository.findById(id)
              .orElseThrow(() -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));

      ticketBuyer.setId(id);

      this.ticketBuyerRepository.save(ticketBuyer);
      return id;
   }

   public void patchTicketBuyer(long id, TicketBuyer ticketBuyer) {
      TicketBuyer currentTicketBuyer = this.ticketBuyerRepository.findById(id)
              .orElseThrow(() -> new TicketBuyerNotFoundException(String.format(TICKET_BUYER_BY_ID_NOT_FOUND_MESSAGE, id)));

      currentTicketBuyer.setFirstName(
              (ticketBuyer.getFirstName() != null) ? ticketBuyer.getFirstName() : currentTicketBuyer.getFirstName()
      );

      currentTicketBuyer.setSecondName(
              (ticketBuyer.getSecondName() != null) ? ticketBuyer.getSecondName() : currentTicketBuyer.getSecondName()
      );

      currentTicketBuyer.setPassword(
              (ticketBuyer.getPassword() != null) ? ticketBuyer.getPassword() : currentTicketBuyer.getPassword()
      );

      currentTicketBuyer.setDateOfBirth(
              (ticketBuyer.getDateOfBirth() != null) ? ticketBuyer.getDateOfBirth() : currentTicketBuyer.getDateOfBirth()
      );


      this.ticketBuyerRepository.save(currentTicketBuyer);
   }

   public void deleteTicketBuyerById(long id) {

   }
}
