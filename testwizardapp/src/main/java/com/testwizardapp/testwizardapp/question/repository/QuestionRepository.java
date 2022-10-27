package com.testwizardapp.testwizardapp.question.repository;

import com.testwizardapp.testwizardapp.question.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query("{subject:'?0'}")
    List<Question> findBySubject(String subject);

    @Query("{subject:'?0', unit:'?1'}")
    List<Question> findBySubjectAndUnit(String subject, String unit);
}
