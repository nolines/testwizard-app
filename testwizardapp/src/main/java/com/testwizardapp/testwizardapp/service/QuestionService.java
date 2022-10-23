package com.testwizardapp.testwizardapp.service;

import com.testwizardapp.testwizardapp.domain.Question;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionService {

    private final FileManager fileManager;

    public void submit(Question question) {
        System.out.println(question.getFileKey());
        fileManager.upload(question.getFileKey(), question.getFile());
    }

}
