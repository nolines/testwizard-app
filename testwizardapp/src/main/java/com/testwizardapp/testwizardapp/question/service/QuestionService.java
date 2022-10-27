package com.testwizardapp.testwizardapp.question.service;

import com.testwizardapp.testwizardapp.question.domain.Question;
import com.testwizardapp.testwizardapp.question.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionService {
    private FileManager fileManager;
    private QuestionRepository questionRepository;

    public void submit(Question question) {
        System.out.println(question.getFileKey());
        questionRepository.save(question);
        fileManager.upload(question.getFileKey(), question.getFile());
    }

    public Optional<Question> get(String id) {
        return questionRepository.findById(id);
    }

    public List<Question> get() {
        return questionRepository.findAll();
    }

    public List<Question> getBySubject(String subject) {
        return questionRepository.findBySubject(subject);
    }

    public List<Question> getBySubjectAndUnit(String subject, String unit) {
        return questionRepository.findBySubjectAndUnit(subject, unit);
    }

    public void delete(String id) {
        questionRepository.deleteById(id);
    }
}
