package com.marcel.malewski.ticketsale.cinemahall;

import com.marcel.malewski.ticketsale.cinemahall.dto.CinemaHallResponseDto;
import com.marcel.malewski.ticketsale.cinemahall.dto.CinemaHallWithValidationDto;
import com.marcel.malewski.ticketsale.cinemahall.exceptions.CinemaHallNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CinemaHallService {
   private final CinemaHallRepository cinemaHallRepository;

   public CinemaHallService(CinemaHallRepository cinemaHallRepository) {
      this.cinemaHallRepository = cinemaHallRepository;
   }

   public List<CinemaHallResponseDto> getAllCinemaHalls() {
      List<CinemaHall> cinemaHalls = cinemaHallRepository.findAll();
      return CinemaHallResponseDto.cinemaHallsResponseDtoFrom(cinemaHalls);
   }

//   public CinemaHall getCinemaHallById(long id) {
//      return this.cinemaHallRepository.findById(id).orElseThrow(() -> new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id)));
//   }

   public void postCinemaHall(CinemaHallWithValidationDto cinemaHallWithValidationDto) {
      CinemaHall cinemaHall = CinemaHall.from(cinemaHallWithValidationDto);
      this.cinemaHallRepository.save(cinemaHall);
   }

   public void putCinemaHallById(long id, CinemaHallWithValidationDto cinemaHallWithValidationDto) {
      CinemaHall cinemaHallToUpdate = this.cinemaHallRepository.findById(id).orElseThrow(
              () -> new CinemaHallNotFoundException(String.format(CinemaHallConstants.CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id)));

      cinemaHallToUpdate.setHallNumber(cinemaHallWithValidationDto.getHallNumber());
      cinemaHallToUpdate.setScreenWidthInMeters(cinemaHallWithValidationDto.getScreenWidthInMeters());
      cinemaHallToUpdate.setScreenHeightInMeters(cinemaHallWithValidationDto.getScreenHeightInMeters());
      cinemaHallToUpdate.setDescription(cinemaHallWithValidationDto.getDescription());

      this.cinemaHallRepository.save(cinemaHallToUpdate);
   }

//   public CinvoidemaHall patchCinemaHallById(long id, CinemaHall cinemaHall) {
//      CinemaHall currentCinemaHall = this.cinemaHallRepository.findById(id)
//              .orElseThrow(() -> new CinemaHallNotFoundException(String.format(CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id)));
//
//      currentCinemaHall.setHallNumber(
//              (cinemaHall.getHallNumber() != null) ? cinemaHall.getHallNumber() : currentCinemaHall.getHallNumber()
//      );
//
//      return this.cinemaHallRepository.save(currentCinemaHall);
//   }

   public void deleteCinemaHallById(long id) {
      if (!this.cinemaHallRepository.existsById(id))
         throw new CinemaHallNotFoundException(String.format(CinemaHallConstants.CINEMA_HALL_BY_ID_NOT_FOUND_MESSAGE, id));

      this.cinemaHallRepository.deleteById(id);
   }
}
