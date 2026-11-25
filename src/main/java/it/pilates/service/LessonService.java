package it.pilates.service;

import it.pilates.dto.LessonResponse;
import it.pilates.entity.Lesson;
import it.pilates.repository.LessonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    public List<LessonResponse> getAllLessons() {

        return lessonRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }
    public List<LessonResponse> getAvailableLessons() {

        return lessonRepository
                .findByAvailableSpotsGreaterThan(0)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private LessonResponse toResponse(Lesson lesson) {

        return new LessonResponse(
                lesson.getId(),
                lesson.getName(),
                lesson.getDate(),
                lesson.getTime(),
                lesson.getInstructor().getName(),
                lesson.getAvailableSpots(),
                lesson.getTotalSpots()
        );
    }

}