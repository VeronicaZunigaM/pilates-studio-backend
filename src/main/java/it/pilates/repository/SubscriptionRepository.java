package it.pilates.repository;

import it.pilates.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByClientIdOrderByExpirationDateAsc(Long clientId);
}