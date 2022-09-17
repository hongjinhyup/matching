package com.example.mongo_pjt.repo;

import com.example.mongo_pjt.domain.dto.QuotationDto;
import com.example.mongo_pjt.domain.entity.QuotationEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuotationRepo extends MongoRepository<QuotationEntity, String> {
    QuotationEntity save(QuotationEntity quotationEntity);
    List<QuotationEntity> findByUserEmail(String email);
    QuotationDto findAllByIdAndStatus(Integer status, String id);
}
