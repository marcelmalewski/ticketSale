package com.marcel.malewski.ticketsale.backend.ticket;

import com.marcel.malewski.ticketsale.backend.seat.Seat;
import com.marcel.malewski.ticketsale.backend.seat.SeatRepository;
import com.marcel.malewski.ticketsale.backend.ticket.dto.TicketResponseDto;
import com.marcel.malewski.ticketsale.backend.ticket.exceptions.TicketNotFoundException;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyer;
import com.marcel.malewski.ticketsale.backend.ticketbuyer.TicketBuyerRepository;
import com.marcel.malewski.ticketsale.front.dto.TicketWithValidation;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.marcel.malewski.ticketsale.backend.ticket.TicketConstants.TICKET_BY_ID_NOT_FOUND_MESSAGE;


@Service
public class TicketService {
   private final TicketRepository ticketRepository;
   private final TicketBuyerRepository ticketBuyerRepository;
   private final SeatRepository seatRepository;

   public TicketService(TicketRepository ticketRepository, TicketBuyerRepository ticketBuyerRepository, SeatRepository seatRepository) {
      this.ticketRepository = ticketRepository;
      this.ticketBuyerRepository = ticketBuyerRepository;
      this.seatRepository = seatRepository;
   }

   public List<TicketResponseDto> getAllTickets() {
      List<Ticket> tickets = ticketRepository.findAll();
      return TicketResponseDto.ticketsResponseDtoFrom(tickets);
   }

   public TicketWithValidation getTicketWithValidationById(long id) {
      Ticket ticket = ticketRepository.findById(id).orElseThrow(
              () -> new TicketNotFoundException(TICKET_BY_ID_NOT_FOUND_MESSAGE + id));
      return TicketWithValidation.from(ticket);
   }

//   public Ticket getTicketById(long id) {
//      return this.ticketRepository.findById(id).orElseThrow(() -> new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id)));
//   }

   public void postTicket(TicketWithValidation ticketWithValidation) {
      TicketBuyer ticketBuyer = this.mapIdToTicketBuyer(ticketWithValidation.getTicketBuyerId());
      List<Seat> seats = this.mapIdsToSeats(ticketWithValidation.getSeatsIds());

      Ticket ticket = Ticket.from(ticketWithValidation, ticketBuyer, seats);
      this.ticketRepository.save(ticket);
   }

   private TicketBuyer mapIdToTicketBuyer(Long ticketBuyerId) {
      return this.ticketBuyerRepository.getReferenceById(ticketBuyerId);
   }

   private List<Seat> mapIdsToSeats(String seatsIds) {
      List<String> seatsIdsAsList = Arrays.stream(seatsIds.split(" ")).toList();
      return seatsIdsAsList.stream()
              .map(seatId -> this.seatRepository.getReferenceById(Long.parseLong(seatId)))
              .collect(Collectors.toList());
   }

   public void putTicketById(long id, TicketWithValidation ticketWithValidation) {
      Ticket ticket = this.ticketRepository.findById(id).orElseThrow(
              () -> new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id)));

      TicketBuyer ticketBuyer = this.mapIdToTicketBuyer(ticketWithValidation.getTicketBuyerId());
      List<Seat> seats = this.mapIdsToSeats(ticketWithValidation.getSeatsIds());
      ticket.setMovieName(ticketWithValidation.getMovieName());
      ticket.setShowDate(ticketWithValidation.getShowDate());
      ticket.setHallNumber(ticketWithValidation.getHallNumber());
      ticket.setTicketBuyer(ticketBuyer);
      ticket.setSeats(seats);

      this.ticketRepository.save(ticket);
   }

//   public Ticket patchTicketById(long id, Ticket ticket) {
//      Ticket currentTicket = this.ticketRepository.findById(id)
//              .orElseThrow(() -> new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id)));
//
//      currentTicket.setMovieName(
//              (ticket.getMovieName() != null) ? ticket.getMovieName() : currentTicket.getMovieName()
//      );
//      currentTicket.setShowDate(
//              (ticket.getShowDate() != null) ? ticket.getShowDate() : currentTicket.getShowDate()
//      );
//      currentTicket.setHallNumber(
//              (ticket.getHallNumber() != null) ? ticket.getHallNumber() : currentTicket.getHallNumber()
//      );
//      currentTicket.setTicketBuyers(
//              (ticket.getTicketBuyers() != null) ? ticket.getTicketBuyers() : currentTicket.getTicketBuyers()
//      );
//
//      return this.ticketRepository.save(currentTicket);
//   }

   public void deleteTicketById(long id) {
      if (!this.ticketRepository.existsById(id))
         throw new TicketNotFoundException(String.format(TICKET_BY_ID_NOT_FOUND_MESSAGE, id));

      this.ticketRepository.deleteById(id);
   }
}
