package com.testwizardapp.testwizardapp.lesson.service;

import com.testwizardapp.testwizardapp.lesson.domain.Lesson;
import com.testwizardapp.testwizardapp.lesson.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class LessonService {
    private final LessonRepository lessonRepository;

    public Lesson addLessonTree(String lessonTree) {
        var tree = Arrays.asList(lessonTree.split(":"));
        var root = tree.get(0);
        var optLesson = lessonRepository.findById(root);
        Lesson result;
        if (optLesson.isEmpty()) {
            result = new Lesson(tree);
        } else {
            result = optLesson.get();
            result.addTree(tree);
        }

        lessonRepository.save(result);
        return result;
    }
}
