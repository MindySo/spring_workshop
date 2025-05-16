package com.ssafy.exam.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.exam.controller.MainFoodController.MainFoodWrapper;
import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dao.FoodDao;
import com.ssafy.exam.model.dao.MainFoodDao;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;

@Service
public class MainFoodServiceImpl implements MainFoodService {

	private final MainFoodDao mainFoodDao;
	private final FoodDao foodDao;
	private final ApiExplorer apiExplorer;
	private final FoodService foodService;

	public MainFoodServiceImpl(MainFoodDao mainFoodDao, FoodDao foodDao, ApiExplorer apiExplorer,
			FoodService foodService) {
		this.mainFoodDao = mainFoodDao;
		this.foodDao = foodDao;
		this.apiExplorer = apiExplorer;
		this.foodService = foodService;
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
			List<MainFoodDetail> nutritions = apiExplorer.fetchNutrientsByFdCode(food.getFoodCode());
			for (MainFoodDetail nut : nutritions) {
				mainFoodDao.updateMainFoodDetail(nut);
			}
		}
	}

	@Override
	public void saveMainFoodWrapper(List<MainFoodWrapper> wrappers) throws Exception {
		for (MainFoodWrapper wrapper : wrappers) {
			MainFood mainFood = wrapper.getMainFood();
			List<Food> foods = wrapper.getFoods();
			List<MainFoodDetail> details = wrapper.getDetails();

			// 메인음식 저장
			mainFoodDao.insertMainFood(mainFood);

			// 구성 요소 음식 저장 (중복 체크 필수)
			for (Food food : foods) {
				if (foodDao.selectFoodByCode(food.getFoodCode()) == null) {
					foodDao.insertFood(food);
				}
			}

			
			
			// 상세정보 저장
			for (MainFoodDetail detail : details) {
				MainFoodDetail existing = mainFoodDao.selectDetail(mainFood.getMainFoodCode(), detail.getFoodCode());
				
				if (existing == null) {
					mainFoodDao.insertMainFoodDetail(detail);
				}
			}
			
			// 4. 💥 영양소 정보 업데이트 추가
		    List<MainFoodDetail> updatedDetails = apiExplorer.fetchDetailsByMainFoodCode(mainFood.getMainFoodCode());
		    for (MainFoodDetail update : updatedDetails) {
		        mainFoodDao.updateMainFoodDetail(update); // 영양소 update
		    }
		}
	}
	
	@Override
    public void saveNutrition(MainFoodDetail detail) {
		mainFoodDao.updateMainFoodDetail(detail);
    }

//    @Override
//    public List<MainFoodDetail> getNutritionByFoodCode(String foodCode) {
//        return mainFoodDao.selectByFoodCode(foodCode);
//    }

}