package com.marcel.malewski.ticketsale.ticketbuyer;

import com.marcel.malewski.ticketsale.ticketbuyer.queryInterface.NumberOfTicketBuyersByAgeRange;
import com.marcel.malewski.ticketsale.ticketbuyer.queryInterface.TicketBuyerCountMoviesByAgeRange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TicketBuyerRepository extends JpaRepository<TicketBuyer, Long> {
   @Query("SELECT tb FROM TicketBuyer tb ORDER BY tb.firstName ASC")
   List<TicketBuyer> findTicketBuyersWithNameOrder();

   @Query("SELECT tb.ageRange as ageRange, count(t) as numberOfTickets FROM TicketBuyer tb" +
           " INNER JOIN Ticket t ON tb.id = t.ticketBuyer.id " +
           "GROUP BY tb.ageRange")
   List<TicketBuyerCountMoviesByAgeRange> countMoviesByAgeRange();

   @Query("SELECT tb.ageRange as ageRange, count(tb) as numberOfTicketBuyers FROM TicketBuyer tb " +
           "GROUP BY tb.ageRange")
   List<NumberOfTicketBuyersByAgeRange> countTicketBuyersByAgeRanges();

   @Query("SELECT tb FROM TicketBuyer tb WHERE tb.dateOfBirth < '2000-01-01'")
   List<TicketBuyer> findTicketBuyersBeforeYear2000();
}
