package it.pilates.repository;

import it.pilates.entity.Booking;
import it.pilates.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByClientIdAndLessonIdAndStatus(Long clientId, Long lessonId, BookingStatus status);

    List<Booking> findByClientIdOrderByCreatedAtDesc(Long clientId);

    List<Booking> findByLessonIdAndStatus(Long lessonId, BookingStatus status);
}