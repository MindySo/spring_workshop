package com.ssafy.exam.controller;

import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;
import com.ssafy.exam.model.service.MainFoodService;
import com.ssafy.exam.model.service.FoodService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/main-food")
public class MainFoodController {

    private final MainFoodService mainFoodService;
    private final FoodService foodService;
    private final ApiExplorer apiExplorer;
    

    public MainFoodController(MainFoodService mainFoodService, FoodService foodService, ApiExplorer apiExplorer) {
		this.mainFoodService = mainFoodService;
		this.foodService = foodService;
		this.apiExplorer = apiExplorer;
	}
    
	
	private static final List<String> keywordList = Arrays.asList(
		    "갈", "감", "강", "개", "게", "고", "곡", "곤", "공", "과", "관", "광", "국", "군", "굴", "김", "깍", "깻", "깡", "까", "꼬", "꽁",
		    "나", "낙", "냉", "너", "노", "녹", "농", "누", "느", "다", "닭", "달", "당", "더", "도", "돌", "동", "돼", "된", "두", "디", "라",
		    "레", "로", "류", "마", "만", "매", "멸", "메", "명", "모", "무", "미", "배", "볶", "보", "부", "비", "사", "삼", "새", "샐", "설",
		    "소", "수", "순", "시", "식", "쌈", "아", "양", "어", "연", "오", "완", "우", "유", "육", "음", "이", "자", "장", "전", "정", "조",
		    "죽", "짜", "찜", "찐", "차", "채", "참", "청", "초", "치", "카", "케", "콩", "탕", "토", "튀", "파", "팥", "편", "포", "피", "한",
		    "해", "햄", "호", "홍"
		);
	
///////////////////////////////// 이거!!!! /////////////////////////////////////////
	@PostMapping("/fetch")
//	public ResponseEntity<String> fetchAndSave(@PathVariable String str) {
    public ResponseEntity<String> fetchAndSave() {
        try {
        	
        	for (char ch = '다'; ch <= '딯'; ch++) {
        	    List<MainFoodWrapper> wrappers = apiExplorer.fetchMainFoodWithDetails(String.valueOf(ch));
        	    mainFoodService.saveMainFoodWrapper(wrappers);  // 👈 여기서 저장
        	}
        	
            return ResponseEntity.ok("저장 완료");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("저장 실패: " + e.getMessage());
        }
    }
    
    @PostMapping("/save")
    public ResponseEntity<String> saveMainFood(@RequestBody MainFoodWrapper wrapper) {
        mainFoodService.saveMainFoodWithDetails(wrapper.getMainFood(), wrapper.getDetails());
        for (Food food : wrapper.getFoods()) {
            foodService.saveFood(food);
        }
        for (MainFoodDetail mfd : wrapper.getDetails()) {
            mainFoodService.saveNutrition(mfd);
        }
        return ResponseEntity.ok("저장 완료");
    }

    @GetMapping("/{code}")
    public ResponseEntity<MainFood> getMainFood(@PathVariable String code) {
        return ResponseEntity.ok(mainFoodService.getMainFoodByCode(code));
    }
    
//    @GetMapping("/fetch")
//    public ResponseEntity<String> fetchAndSaveFromOpenApi(@RequestParam String foodName) throws Exception {
//        try {
//            mainFoodService.fetchAndSaveMainFoodByName(foodName);
//            return ResponseEntity.ok("API에서 데이터 가져와서 저장 완료");
//        } catch (IOException e) {
//            return ResponseEntity.internalServerError().body("오류 발생: " + e.getMessage());
//        }
//    }

    public static class MainFoodWrapper {
        private MainFood mainFood;
        private List<MainFoodDetail> details;
        private List<Food> foods;

        public MainFood getMainFood() {
            return mainFood;
        }

        public void setMainFood(MainFood mainFood) {
            this.mainFood = mainFood;
        }

        public List<MainFoodDetail> getDetails() {
            return details;
        }

        public void setDetails(List<MainFoodDetail> details) {
            this.details = details;
        }

        public List<Food> getFoods() {
            return foods;
        }

        public void setFoods(List<Food> foods) {
            this.foods = foods;
        }
    }
}
