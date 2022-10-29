package com.testwizardapp.testwizardapp.question.controller;

import com.testwizardapp.testwizardapp.question.domain.Question;
import com.testwizardapp.testwizardapp.question.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
@Slf4j
@AllArgsConstructor
public class QuestionController {
    private QuestionService questionService;

    @PostMapping
    void submitQuestions(@RequestBody @Valid QuestionCreateRequest req) {
        questionService.submit(req.file(), req.subject(), req.unit(), req.level(), req.answer());
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
        questionService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unit")
    ResponseEntity<List<Question>> getQuestionByUnit(@PathVariable(value = "unit") String unit) {
        return ResponseEntity.ok().body(questionService.getByUnit(unit));
    }
}
