package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.dto.SurveyDto;

import com.example.mongo_pjt.domain.dto.UserDto;
import com.example.mongo_pjt.domain.entity.SurveyEntity;
import com.example.mongo_pjt.repo.SurveyRepo;
import com.example.mongo_pjt.service.SurveyServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyServiceImpl surveyService;
    private final SurveyRepo surveyRepo;

    @Operation(summary = "test hello", description = "hello api example")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })
    @PostMapping("/category/{bigId}/survey/{smallId}/save")
    public ResponseEntity saveSurvey(@RequestBody SurveyDto s) {
        log.info(s);
        SurveyEntity savingStatus = surveyService.savingSurvey(s);
        return ResponseEntity.ok().body(savingStatus);
    }


    /*------------------------------------------------------------------------------------------------------------------------*/
    @GetMapping("survey/all")
    public ResponseEntity showAll() {
        return ResponseEntity.ok().body(surveyService.showingAllSurveys());
    }

    // 요청 리스트 -> 특정 요청 값 + 본인 정보
    @PostMapping(value = "/quotation/{id}", produces="application/json;charset=UTF-8")
    public ResponseEntity matchedDetail(@PathVariable String id, @RequestBody SurveyDto surveyDto) {

        return ResponseEntity.ok().body(surveyService.showingSurveyDetail(id, surveyDto));
        // return 값에 고수정보를 surveyDto에 담아서 같이 보내준다.
    }

    // 특정 survey 조회
    // 실제 url -> /matchedList
//    @PostMapping("/matchedList")
//    public ResponseEntity matchedList(@RequestBody UserDto userEntity) {
//        return ResponseEntity.ok().body(surveyService.showingSpecificCategory(userEntity));
//    }

    @PostMapping("/matchedList")
    public ResponseEntity matchedList2(@RequestBody UserDto userDto) {
        log.info("user address : " + userDto.getAddress());
        return ResponseEntity.ok().body(surveyService.showingProperUsersForExpert(userDto));
    }
// 삭제 기능
/*------------------------------------------------------------*/
//    @DeleteMapping("/delete")
//    public ResponseEntity deleteAll() {
//        surveyRepo.deleteAll();
//        return ResponseEntity.ok().body("삭제 완료");
//    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable String id) {
        SurveyEntity surveyEntity = surveyRepo.findById(id).orElseThrow();

        surveyRepo.deleteById(surveyEntity.getId());

        if (surveyRepo.findById(id) == null) {
            log.info("삭제 예정인 의뢰서 : " + surveyEntity.getId());
            return ResponseEntity.ok().body(id + "번 작성된 의뢰서 삭제 완료");
        } else {
            return ResponseEntity.ok().body("삭제 안됨");
        }
    }
/*------------------------------------------------------------*/
}
