package com.testwizardapp.testwizardapp.lesson.controller;

import com.testwizardapp.testwizardapp.lesson.domain.Lesson;
import com.testwizardapp.testwizardapp.lesson.service.LessonService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
@RequestMapping("/lessons")
@AllArgsConstructor
public class LessonController {
    public static final Locale TR = new Locale("tr", "TR");
    private LessonService lessonService;

    ResponseEntity<Lesson> generateLessonTree(@RequestBody LessonRequest lr) {
        return ResponseEntity.ok(lessonService.addLessonTree(lr.lessonTree()));
    }
}
