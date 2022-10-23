package com.testwizardapp.testwizardapp.controller;

import com.testwizardapp.testwizardapp.domain.Question;
import com.testwizardapp.testwizardapp.service.QuestionService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping("/questions")
    void submitQuestions(@RequestBody Question question) {
        questionService.submit(question);
    }
}
