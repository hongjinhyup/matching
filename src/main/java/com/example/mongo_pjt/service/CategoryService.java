package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.entity.Category;
import com.example.mongo_pjt.domain.entity.Info;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface CategoryService {

    List<Category> showingCategories();
    List showingEachCategory(Long _id);
    Info showingDetail(Long _id, Integer indexNum);

}
