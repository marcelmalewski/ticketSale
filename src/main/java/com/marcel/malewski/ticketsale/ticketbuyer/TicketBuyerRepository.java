package com.marcel.malewski.ticketsale.ticketbuyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketBuyerRepository extends JpaRepository<TicketBuyer, Long> {
//   @Query("" +
//           "SELECT tb FROM TicketBuyer tb" +
//           " INNER JOIN tb.tickets tickets " +
//           " WHERE tb.id = ?1 AND tickets.id = 1")

   @Query("" +
           "SELECT tb FROM TicketBuyer tb" +
           " INNER JOIN tb.tickets tickets ON tickets.id = 1" +
           " WHERE tb.id = ?1")
   Optional<TicketBuyer> findTicketBuyerMovies(Long id, String movieName);
}
