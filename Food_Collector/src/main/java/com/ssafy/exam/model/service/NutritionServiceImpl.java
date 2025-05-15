package com.ssafy.exam.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exam.model.dao.NutritionDao;
import com.ssafy.exam.model.dto.Nutrition;

@Service
public class NutritionServiceImpl implements NutritionService {

    private final NutritionDao nutritionDao;

	public NutritionServiceImpl(NutritionDao nutritionDao) {
		super();
		this.nutritionDao = nutritionDao;
	}

	@Override
    public void saveNutrition(Nutrition nutrition) {
        nutritionDao.insertNutrition(nutrition);
    }

    @Override
    public List<Nutrition> getNutritionByFoodCode(String foodCode) {
        return nutritionDao.selectByFoodCode(foodCode);
    }
    
}
