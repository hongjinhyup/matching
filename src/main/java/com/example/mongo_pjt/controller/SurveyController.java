package com.example.mongo_pjt.controller;

import com.example.mongo_pjt.domain.dto.*;

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

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
public class SurveyController {
    private final SurveyServiceImpl surveyService;
    private final SurveyRepo surveyRepo;


    @Operation(summary = "요청서 CRUD 담당 Controller", description = "요청서 관리")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK !!"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST !!"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND !!"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR !!")
    })

    // 요청서 저장
    @PostMapping("/category/{bigId}/survey/{smallId}/save")
    public ResponseEntity saveSurvey(@RequestBody SurveyDto s) {
        log.info(s.getEmail());
        SurveyEntity savingStatus = surveyService.savingSurvey(s);
        return ResponseEntity.ok().body(savingStatus);
    }

    // survey id 변경
    @GetMapping("/survey/status/{id}")
    public void changeStatus(@PathVariable String id) {
        surveyService.changingStatus(id);
    }

    // 요청 리스트 -> 특정 요청 값 + 본인 정보
    @PostMapping(value = "/quotation/{id}")
    public ResponseEntity matchedDetail(@PathVariable String id, @RequestBody SurveyDto surveyDto) {

        return ResponseEntity.ok().body(surveyService.showingSurveyDetail(id, surveyDto));
        // return 값에 고수정보를 surveyDto에 담아서 같이 보내준다.
    }


    // 전문가 입장 사용자들의 요청서 확인
    @PostMapping("/matchedList")
    public ResponseEntity matchedList(@RequestBody UserDto userDto) {
        return ResponseEntity.ok().body(surveyService.showingProperUsersForExpert(userDto));
    }

    // 진행 전 중 후 매핑 요청에 따른 값 전달
    @PostMapping("/matchedList/{status}") // 오류 해결 과정 -> https://www.notion.so/632ef042d327441092a013df2976c0da
    public ResponseEntity surveyInfoAccordingToStatus(@PathVariable Integer status, @RequestBody IdListDto idListDto) {
        log.info("status : " + status);
        log.info("surveyIdListDto : " + idListDto.getId());

        List<SurveyOnlyDto> surveyOnlyDtoList = surveyService.showingSurveysAccordingToStatus(status, idListDto);

        return ResponseEntity.ok().body(surveyOnlyDtoList);
    }



    /*----------------------------------------------------------------------------------*/

    // 프론트 구현 안됨
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

    // 테스트용 API
    @GetMapping("survey/all")
    public ResponseEntity showAll() {
        return ResponseEntity.ok().body(surveyService.showingAllSurveys());
    }

    @PutMapping("/survey/matchedfinish")
    public ResponseEntity matchedfinish(@RequestBody QuotationDto users){
        surveyService.statusFinish(users);
        return ResponseEntity.ok().body("Status Changed");
    }
}












