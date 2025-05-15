package com.ssafy.exam.controller;

import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;
import com.ssafy.exam.model.dto.Nutrition;
import com.ssafy.exam.model.service.MainFoodService;
import com.ssafy.exam.model.service.FoodService;
import com.ssafy.exam.model.service.NutritionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/main-food")
public class MainFoodController {

    private final MainFoodService mainFoodService;
    private final FoodService foodService;
    private final NutritionService nutritionService;
    private final ApiExplorer apiExplorer;

    public MainFoodController(MainFoodService mainFoodService, FoodService foodService,
			NutritionService nutritionService, ApiExplorer apiExplorer) {
		this.mainFoodService = mainFoodService;
		this.foodService = foodService;
		this.nutritionService = nutritionService;
		this.apiExplorer = apiExplorer;
	}


	@PostMapping("/fetch/{foodName}")
    public ResponseEntity<String> fetchAndSave(@PathVariable String foodName) {
        try {
            List<MainFoodWrapper> wrappers = apiExplorer.fetchMainFoodWithDetails(foodName);
            mainFoodService.saveMainFoodWrapper(wrappers);  // 👈 여기서 저장
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
        for (Nutrition nut : wrapper.getNutritions()) {
            nutritionService.saveNutrition(nut);
        }
        return ResponseEntity.ok("저장 완료");
    }

    @GetMapping("/{code}")
    public ResponseEntity<MainFood> getMainFood(@PathVariable String code) {
        return ResponseEntity.ok(mainFoodService.getMainFoodByCode(code));
    }
    
    @GetMapping("/fetch")
    public ResponseEntity<String> fetchAndSaveFromOpenApi(@RequestParam String foodName) throws Exception {
        try {
            mainFoodService.fetchAndSaveMainFoodByName(foodName);
            return ResponseEntity.ok("API에서 데이터 가져와서 저장 완료");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("오류 발생: " + e.getMessage());
        }
    }

    public static class MainFoodWrapper {
        private MainFood mainFood;
        private List<MainFoodDetail> details;
        private List<Food> foods;
        private List<Nutrition> nutritions;

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

        public List<Nutrition> getNutritions() {
            return nutritions;
        }

        public void setNutritions(List<Nutrition> nutritions) {
            this.nutritions = nutritions;
        }
    }
}
