package com.testwizardapp.testwizardapp.question.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "question")
public class Question {
    @Id
    private String id;
    private byte[] file;
    private String fileKey;
    private String unit;
    private String subject;
    private Level level;
    private String answer;
}

