package com.testwizardapp.testwizardapp.lesson.controller;

import com.testwizardapp.testwizardapp.lesson.domain.Lesson;
import com.testwizardapp.testwizardapp.lesson.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController("/lessons")
@AllArgsConstructor
public class LessonController {
    public static final Locale TR = new Locale("tr", "TR");
    private LessonService lessonService;

    @PostMapping
    ResponseEntity<Lesson> submitLessonTree(@RequestBody LessonRequest lr) {
        return ResponseEntity.ok(lessonService.addLessonTree(lr.getLessonTree().toLowerCase(TR)));
    }
}
