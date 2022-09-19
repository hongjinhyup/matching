package com.example.mongo_pjt.repo;

import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.dto.SurveyDto;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SurveyRepo extends MongoRepository<SurveyEntity, String> {
    Optional<SurveyEntity> findById(String id);
    void deleteById(String id);
    SurveyEntity findByEmailAndCategory(String email, String category);
    @Query("{category :  ?0}")
    List<SurveyEntity> findByCategory(String category);
    List<SurveyEntity> findSurveyEntitiesByRegionAndAgeAndGender(String region, Integer age, String gender);
    SurveyDto findAllByIdAndStatus(String id, Integer status);
    SurveyDto findByEmailAndGosuEmail(String email, String gosuEmail);
}
