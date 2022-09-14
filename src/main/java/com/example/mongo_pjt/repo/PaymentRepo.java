package com.example.mongo_pjt.repo;

import com.example.mongo_pjt.domain.dto.PaymentDto;
import com.example.mongo_pjt.domain.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepo extends MongoRepository<PaymentEntity, String> {
    PaymentDto findByUserName(String userName);
    PaymentDto findByExpertName(String expertName);
}
