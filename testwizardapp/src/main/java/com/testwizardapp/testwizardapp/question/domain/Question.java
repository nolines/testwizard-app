package com.testwizardapp.testwizardapp.question.domain;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "question")
@Data
public class Question {
    @Id
    private String id;
    @NonNull
    private String fileKey;
    @NonNull
    private String subject;
    @NonNull
    private String unit;
    @NonNull
    private Level level;
    @NonNull
    private String answer;
    @NonNull
    private boolean deleted;
}

