package com.testwizardapp.testwizardapp.service;

import com.testwizardapp.testwizardapp.domain.Question;
import com.testwizardapp.testwizardapp.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public record QuestionService(FileManager fileManager,
                              QuestionRepository questionRepository) {

    public void submit(Question question) {
        System.out.println(question.getFileKey());
        fileManager.upload(question.getFileKey(), question.getFile());
    }

    public Optional<Question> get(String id) {
        return questionRepository.findById(id);
    }

    public List<Question> get() {
        return questionRepository.findAll();
    }

    public List<Question> getByUnit(String unit) {
        return questionRepository.findByUnit(unit);
    }

    public void delete(String id) {
        questionRepository.deleteById(id);
    }
}
