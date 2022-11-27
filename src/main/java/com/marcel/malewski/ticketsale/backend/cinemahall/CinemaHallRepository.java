package com.marcel.malewski.ticketsale.backend.cinemahall;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaHallRepository extends JpaRepository<CinemaHall, Long> {}
