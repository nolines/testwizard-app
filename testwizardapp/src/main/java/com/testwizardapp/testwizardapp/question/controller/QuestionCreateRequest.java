package com.testwizardapp.testwizardapp.question.controller;

import com.testwizardapp.testwizardapp.question.domain.Level;

import javax.validation.constraints.NotNull;


public record QuestionCreateRequest(
        @NotNull
        String file,
        @NotNull
        String unit,
        @NotNull
        String subject,
        @NotNull
        Level level,
        @NotNull
        String answer
) {
}