package it.pilates.repository;

import it.pilates.entity.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonRepository
        extends JpaRepository<Lesson, Long> {
    List<Lesson> findByInstructorId(Long instructorId);
    List<Lesson> findByAvailableSpotsGreaterThan(Integer spots);
}