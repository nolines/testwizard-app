package com.testwizardapp.testwizardapp.question.domain;

import lombok.Data;

@Data
public class Question {
    private String id;

    private byte[] file;
    private String fileKey;
    private String unit;
    private String subject;
    private Level level;
    private String answer;
}

