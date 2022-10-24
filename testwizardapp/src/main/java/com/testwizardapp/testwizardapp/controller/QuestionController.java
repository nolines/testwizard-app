package com.testwizardapp.testwizardapp.controller;

import com.testwizardapp.testwizardapp.domain.Question;
import com.testwizardapp.testwizardapp.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@AllArgsConstructor
public record QuestionController(QuestionService questionService) {

    @PostMapping("/questions")
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
