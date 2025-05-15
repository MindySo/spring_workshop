package com.ssafy.exam.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dao.FoodDao;
import com.ssafy.exam.model.dao.MainFoodDao;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;
import com.ssafy.exam.model.dto.Nutrition;

@Service
public class MainFoodServiceImpl implements MainFoodService {

    private final MainFoodDao mainFoodDao;
    private final FoodDao foodDao;
    private final ApiExplorer apiExplorer;
    private final FoodService foodService;
    private final NutritionService nutritionService;

    public MainFoodServiceImpl(MainFoodDao mainFoodDao, FoodDao foodDao, ApiExplorer apiExplorer,
			FoodService foodService, NutritionService nutritionService) {
		this.mainFoodDao = mainFoodDao;
		this.foodDao = foodDao;
		this.apiExplorer = apiExplorer;
		this.foodService = foodService;
		this.nutritionService = nutritionService;
	}

	@Transactional
    @Override
    public void saveMainFoodWithDetails(MainFood mainFood, List<MainFoodDetail> details) {
        mainFoodDao.insertMainFood(mainFood);
        for (MainFoodDetail detail : details) {
            mainFoodDao.insertMainFoodDetail(detail);
        }
    }

    @Override
    public MainFood getMainFoodByCode(String code) {
        return mainFoodDao.selectMainFoodByCode(code);
    }
    

    @Override
    public void fetchAndSaveMainFoodByName(String foodName) throws Exception {
        List<Food> foods = apiExplorer.fetchFoodsByName(foodName);

        for (Food food : foods) {
            // 중복 검사 후 저장
            if (foodDao.selectFoodByCode(food.getFoodCode()) == null) {
                foodService.saveFood(food);
            }

            // 영양소 정보 가져오기
            List<Nutrition> nutritions = apiExplorer.fetchNutrientsByFdCode(food.getFoodCode());
            for (Nutrition nut : nutritions) {
                nutritionService.saveNutrition(nut);
            }
        }
    }
}