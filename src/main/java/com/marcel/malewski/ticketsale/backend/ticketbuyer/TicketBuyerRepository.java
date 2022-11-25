package com.marcel.malewski.ticketsale.backend.ticketbuyer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketBuyerRepository extends JpaRepository<TicketBuyer, Long> {
}
