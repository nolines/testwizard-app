package com.testwizardapp.testwizardapp.question.controller;

import com.testwizardapp.testwizardapp.question.domain.Question;
import com.testwizardapp.testwizardapp.question.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@Slf4j
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping
    void submitQuestions(@RequestBody Question question) {
        questionService.submit(question);
    }

    @GetMapping("/{id}")
    ResponseEntity<Optional<Question>> getQuestion(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok().body(questionService.get(id));
    }

    @GetMapping("/list")
    ResponseEntity<List<Question>> list() {
        return ResponseEntity.ok().body(questionService.get());
    }

    @DeleteMapping("/{id}")
    ResponseEntity delete(@PathVariable(value = "id") String id) {
        var success = questionService.delete(id);

        if (success) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = {"/subject/{subject}/{unit}", "/subject/{subject}"})
    ResponseEntity<List<Question>> getQuestionByUnit(
            @PathVariable(value = "subject") String subject,
            @PathVariable(value = "unit") Optional<String> unit) {

        return unit.isPresent() ?
                ResponseEntity.ok().body(questionService.getBySubjectAndUnit(subject, unit.get())) :
                ResponseEntity.ok().body(questionService.getBySubject(subject));
    }
}
