package com.marcel.malewski.ticketsale.backend.seat;

import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHall;
import com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHallRepository;
import com.marcel.malewski.ticketsale.backend.seat.dto.SeatResponseDto;
import com.marcel.malewski.ticketsale.backend.seat.exceptions.SeatNotFoundException;
import com.marcel.malewski.ticketsale.front.dto.SeatWithValidationDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
   private final SeatRepository seatRepository;
   private final CinemaHallRepository cinemaHallRepository;

   public SeatService(SeatRepository seatRepository, CinemaHallRepository cinemaHallRepository) {
      this.seatRepository = seatRepository;
      this.cinemaHallRepository = cinemaHallRepository;
   }

   public List<SeatResponseDto> getAllSeats() {
      List<Seat> seats = seatRepository.findAll();
      return SeatResponseDto.seatsResponseDtoFrom(seats);
   }

   public Seat getSeatById(Long id) {
      return seatRepository.findById(id)
              .orElseThrow(() -> new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public Seat postSeat(SeatWithValidationDto seatWithValidationDto) {
      CinemaHall cinemaHall = this.cinemaHallRepository.getReferenceById(seatWithValidationDto.getCinemaHallId());
      Seat seat = Seat.from(seatWithValidationDto, cinemaHall);

      return seatRepository.save(seat);
   }

   public Seat putSeatById(Long id, SeatWithValidationDto seatWithValidationDto) {
      Seat seatToUpdate = this.seatRepository.findById(id)
              .orElseThrow(() -> new SeatNotFoundException(String.format(SeatConstants.SEAT_BY_ID_NOT_FOUND_MESSAGE, id)));

      CinemaHall cinemaHall = this.cinemaHallRepository.getReferenceById(seatWithValidationDto.getCinemaHallId());
      seatToUpdate.setSeatNumber(seatWithValidationDto.getSeatNumber());
      seatToUpdate.setIsPremium(seatWithValidationDto.getIsPremium());
      seatToUpdate.setCinemaHall(cinemaHall);

      return seatRepository.save(seatToUpdate);
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
