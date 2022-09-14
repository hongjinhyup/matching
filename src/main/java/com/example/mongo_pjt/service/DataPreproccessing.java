package com.example.mongo_pjt.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataPreproccessing {

    // 전문가 주소값들 split -> 조건 달기
    public List splitString(String expertAddress) {

        String excepts = "[^\uAC00-\uD7A30-9a-zA-Z,]";
        String cleanAddr = expertAddress.replaceAll(excepts, "");
        String[] eachAddressList = cleanAddr.split(",");

        List ls = new ArrayList();

        for (int i=0; i<eachAddressList.length; i++) {
            String addr = eachAddressList[i];
            ls.add(addr);
        }
        return ls;
    }
}
