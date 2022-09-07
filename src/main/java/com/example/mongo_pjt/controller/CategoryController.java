package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.entity.Category;
import com.example.mongo_pjt.domain.entity.Info;
import com.example.mongo_pjt.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    // 전체 카테고리 조회
    @GetMapping("/categories")
    public ResponseEntity showAll() {
        List<Category> a = categoryService.showingCategories();
        log.info("로그기록 🐳 🫧 : " + a);
        return ResponseEntity.ok().body(a);
    }

    // 특정 카테고리 상세설명 조회 (MongoRepo 및 list indexing 이용)
    @GetMapping("/category/{categoryId}")
    public ResponseEntity showEachCategory(@PathVariable Long categoryId) {
        List b = categoryService.showingEachCategory(categoryId);
        return ResponseEntity.ok().body(b);
    }

    @GetMapping("/category/{categoryId}/survey/{indexNum}")
    public ResponseEntity showCategoryInfo(@PathVariable Long categoryId, @PathVariable Integer indexNum) {
        Info info = categoryService.showingDetail(categoryId, indexNum);
        return ResponseEntity.ok().body(info);
    }
}
