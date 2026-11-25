package it.pilates.repository;

import it.pilates.entity.CreditTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditTransactionRepository extends JpaRepository<CreditTransaction, Long> {

    List<CreditTransaction> findBySubscriptionIdOrderByCreatedAtAsc(Long subscriptionId);
}