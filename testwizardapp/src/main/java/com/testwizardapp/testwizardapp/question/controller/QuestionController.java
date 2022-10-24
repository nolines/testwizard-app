package com.testwizardapp.testwizardapp.question.controller;

import com.testwizardapp.testwizardapp.question.domain.Question;
import com.testwizardapp.testwizardapp.question.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/questions")
@Slf4j
public record QuestionController(QuestionService questionService) {

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
    void delete(@PathVariable(value = "id") String id) {
        questionService.delete(id);
    }

    @GetMapping("/unit")
    ResponseEntity<List<Question>> getQuestionByUnit(@PathVariable(value = "unit") String unit) {
        return ResponseEntity.ok().body(questionService.getByUnit(unit));
    }
}
