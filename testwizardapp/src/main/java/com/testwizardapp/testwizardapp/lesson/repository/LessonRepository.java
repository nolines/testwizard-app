package com.testwizardapp.testwizardapp.lesson.repository;

import com.testwizardapp.testwizardapp.lesson.domain.Lesson;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;

@Service
public class LessonRepository {
    private static final Map<String, Lesson> rootLessonCache = new ConcurrentSkipListMap<>();

    public Lesson save(Lesson lesson) {
        rootLessonCache.put(lesson.getId(), lesson);
        return lesson;
    }

    public Optional<Lesson> findById(String id) {
        return Optional.ofNullable(rootLessonCache.get(id));
    }
}
