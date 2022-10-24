package com.testwizardapp.testwizardapp.repository;

import com.testwizardapp.testwizardapp.domain.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends MongoRepository<Question, String> {
    @Query(value="{unit:'?0'}")
    List<Question> findByUnit(String unit);
}
