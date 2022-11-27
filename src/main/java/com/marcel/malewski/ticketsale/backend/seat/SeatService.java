package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.seat.exceptions.SeatNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
   private final SeatRepository seatRepository;

   public SeatService(SeatRepository seatRepository) {
      this.seatRepository = seatRepository;
   }

   public List<Seat> getAllSeats() {
      return seatRepository.findAll();
   }

   public Seat getSeatById(Long id) {
      return seatRepository.findById(id)
              .orElseThrow(() -> new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public Seat postSeat(Seat seat) {
      return seatRepository.save(seat);
   }

   public Seat putSeatById(Long id, Seat seat) {
      if(!seatRepository.existsById(id))
         throw new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id));

      seat.setId(id);
      return seatRepository.save(seat);
   }

   public Seat patchSeatById(Long id, Seat seat) {
      Seat currentSeat = seatRepository.findById(id)
              .orElseThrow(() -> new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id)));

      currentSeat.setSeatNumber(
              (seat.getSeatNumber() != null) ? seat.getSeatNumber() : currentSeat.getSeatNumber()
      );
      currentSeat.setIsPremium(
              (seat.getIsPremium() != null) ? seat.getIsPremium() : currentSeat.getIsPremium()
      );

      return seatRepository.save(currentSeat);
   }

   public void deleteSeatById(Long id) {
      if(!seatRepository.existsById(id))
         throw new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id));

      seatRepository.deleteById(id);
   }
}
