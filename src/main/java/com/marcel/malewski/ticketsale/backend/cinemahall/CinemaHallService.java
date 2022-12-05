package com.marcel.malewski.ticketsale.backend.cinemahall;

import com.marcel.malewski.ticketsale.backend.cinemahall.dto.CinemaHallResponseDto;
import com.marcel.malewski.ticketsale.backend.cinemahall.exceptions.CinemaHallNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.marcel.malewski.ticketsale.backend.cinemahall.CinemaHallConstants.CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE;

@Service
public class CinemaHallService {
   private final CinemaHallRepository cinemaHallRepository;

   public CinemaHallService(CinemaHallRepository cinemaHallRepository) {
      this.cinemaHallRepository = cinemaHallRepository;
   }

   public List<CinemaHallResponseDto> getAllCinemaHalls() {
      List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
      return CinemaHallResponseDto.cinemaHallsResponseDtoFromCinemaHalls(cinemaHalls);
   }

   public CinemaHall getCinemaHallById(long id) {
      return this.cinemaHallRepository.findById(id).orElseThrow(() -> new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id)));
   }

   public CinemaHall postCinemaHall(CinemaHall cinemaHall) {
      return this.cinemaHallRepository.save(cinemaHall);
   }

   public CinemaHall putCinemaHallById(long id, CinemaHall cinemaHall) {
      if(!this.cinemaHallRepository.existsById(id))
         throw new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id));

      cinemaHall.setId(id);
      return this.cinemaHallRepository.save(cinemaHall);
   }

   public CinemaHall patchCinemaHallById(long id, CinemaHall cinemaHall) {
      CinemaHall currentCinemaHall = this.cinemaHallRepository.findById(id)
              .orElseThrow(() -> new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id)));

      currentCinemaHall.setHallNumber(
              (cinemaHall.getHallNumber() != null) ? cinemaHall.getHallNumber() : currentCinemaHall.getHallNumber()
      );

      return this.cinemaHallRepository.save(currentCinemaHall);
   }

   public void deleteCinemaHallById(long id) {
      if(!this.cinemaHallRepository.existsById(id))
         throw new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id));

      this.cinemaHallRepository.deleteById(id);
   }
}
