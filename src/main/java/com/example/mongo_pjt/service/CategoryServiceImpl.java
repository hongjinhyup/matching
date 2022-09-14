package com.example.mongo_pjt.service;

import com.example.mongo_pjt.domain.entity.Category;
import com.example.mongo_pjt.domain.entity.Info;
import com.example.mongo_pjt.repo.CategoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepo categoryRepo;

    @Override
    public List<Category> showingCategories() {
        List<Category> a = categoryRepo.findAll();
        log.info("🐳" + a);
        return a;
    }

    @Override
    public List showingEachCategory(Long _id) {
        Category categoryInfo = categoryRepo.findBy_id(_id);
        log.info("요청 URL 확인 할 것 : " + categoryInfo);
        Map<String, String> map = new HashMap<String, String>();
        List lists = new ArrayList();

        // 프론트에서 필요한 정보만 받기 위함
        for (int i=0; i<=8; i++)
        {
            map = new HashMap<>();
            Long idx = categoryInfo.getInfo().get(i).get_idx();
            map.put("_idx", idx.toString());
            String name = categoryInfo.getInfo().get(i).getName();
            map.put("name", name);
            String image = categoryInfo.getInfo().get(i).getImage();
            map.put("image", image);
            lists.add(map);
        }
        return lists;
    }

    @Override
    public Info showingDetail(Long _id, Integer indexNum) {
        Category smallCategory = categoryRepo.findBy_id(_id);
        Info info = smallCategory.getInfo().get(indexNum);
        return info;
    }
}
