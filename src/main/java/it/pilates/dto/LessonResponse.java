package it.pilates.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record LessonResponse(
        Long id,
        String name,
        LocalDate date,
        LocalTime time,
        String instructorName,
        Integer availableSpots,
        Integer totalSpots
) {}