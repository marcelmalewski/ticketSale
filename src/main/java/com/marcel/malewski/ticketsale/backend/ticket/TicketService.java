package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.ticket.exceptions.TicketNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.backend.ticket.TicketConstants.TICKET_BY_ID_NOT_FOUND_MESSAGE;


@Service
public class TicketService {
   private final TicketRepository ticketRepository;

   public TicketService(TicketRepository ticketRepository) {
      this.ticketRepository = ticketRepository;
   }

   public List<Ticket> getAllTickets() {
      return this.ticketRepository.findAll();
   }

   public Ticket getTicketById(long id) {
      return this.ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public Ticket postTicket(Ticket ticket) {
      return this.ticketRepository.save(ticket);
   }

   public Ticket putTicketById(long id, Ticket ticket) {
      if (!this.ticketRepository.existsById(id))
         throw new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id));

      ticket.setId(id);
      return this.ticketRepository.save(ticket);
   }

   public Ticket patchTicketById(long id, Ticket ticket) {
      Ticket currentTicket = this.ticketRepository.findById(id)
              .orElseThrow(() -> new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id)));

      currentTicket.setMovieName(
              (ticket.getMovieName() != null) ? ticket.getMovieName() : currentTicket.getMovieName()
      );
      currentTicket.setShowDate(
              (ticket.getShowDate() != null) ? ticket.getShowDate() : currentTicket.getShowDate()
      );
      currentTicket.setHallNumber(
              (ticket.getHallNumber() != null) ? ticket.getHallNumber() : currentTicket.getHallNumber()
      );
      currentTicket.setTicketBuyers(
              (ticket.getTicketBuyers() != null) ? ticket.getTicketBuyers() : currentTicket.getTicketBuyers()
      );

      return this.ticketRepository.save(currentTicket);
   }

   public void deleteTicketById(long id) {
      if (!this.ticketRepository.existsById(id))
         throw new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id));

      this.ticketRepository.deleteById(id);
   }
}
