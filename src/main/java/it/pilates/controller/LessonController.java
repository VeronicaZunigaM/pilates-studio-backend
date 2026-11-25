package it.pilates.controller;

import it.pilates.dto.LessonResponse;
import it.pilates.entity.Lesson;
import it.pilates.service.LessonService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public List<LessonResponse> getAllLessons() {
        return lessonService.getAllLessons();
    }

    @GetMapping("/available")
    public List<LessonResponse> getAvailableLessons() {
        return lessonService.getAvailableLessons();
    }
}