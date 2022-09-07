package com.example.mongo_pjt.repo;

import com.example.mongo_pjt.domain.entity.Category;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepo extends MongoRepository<Category, Long> {

    Category findBy_id(Long _id);

}
