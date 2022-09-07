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
        Category a = categoryRepo.findBy_id(_id);
        log.info("a가 왜? : " + a);
        Map<String, String> map = new HashMap<String, String>();
        List lists = new ArrayList();

        int i;
        for (i=0; i<=8; i++)
        {
            map = new HashMap<>();
            Long idx = a.getInfo().get(i).get_idx();
            map.put("_idx", idx.toString());
            log.info("id가 왜 null 일까?  " + a.getInfo());
            String name = a.getInfo().get(i).getName();
            map.put("name", name);
            String image = a.getInfo().get(i).getImage();
            map.put("image", image);
            lists.add(map);
        }
        log.info("b : " + map);
        return lists;
    }

    @Override
    public Info showingDetail(Long _id, Integer indexNum) {
        Category a = categoryRepo.findBy_id(_id);
        Info info = a.getInfo().get(indexNum);
        return info;
    }
}
