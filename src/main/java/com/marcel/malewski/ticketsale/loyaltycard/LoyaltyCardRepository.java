package com.marcel.malewski.ticketsale.loyaltycard;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyCardRepository extends JpaRepository<LoyaltyCard, Long> {
   @Query("SELECT avg(lc.moneySpent) FROM LoyaltyCard lc")
   Float getAverageSpentMoney();
}
