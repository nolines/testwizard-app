package com.testwizardapp.testwizardapp.question.service;

import com.testwizardapp.testwizardapp.question.domain.Level;
import com.testwizardapp.testwizardapp.question.domain.Question;
import com.testwizardapp.testwizardapp.question.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class QuestionService {
    private FileManager fileManager;
    private QuestionRepository questionRepository;

    public void submit(String file, String subject, String unit, Level level, String answer) {
        var question = new Question(UUID.randomUUID().toString(), subject, unit, level, answer, false);
        fileManager.upload(question.getFileKey(), file);
        question = questionRepository.save(question);
        log.info("Question saved with id {}, fileKey {}", question.getId(), question.getFileKey());
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
        var question = questionRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Question with the id %s not found.", id)));

        question.setDeleted(true);
        questionRepository.save(question);
        fileManager.remove(question.getFileKey());
    }

    public List<Question> getBy(String subject, Optional<String> unit, Optional<Level> level) {
        if (unit.isEmpty() && level.isEmpty()) {
            return questionRepository.findBySubject(subject);
        } else if (unit.isPresent() && level.isEmpty()) {
            return questionRepository.findBySubjectAndUnit(subject, unit.get());
        } else if (unit.isEmpty() && level.isPresent()) {
            return questionRepository.findBySubjectAndLevel(subject, level.get());
        }
        return questionRepository.findBySubject(subject);
    }
}
