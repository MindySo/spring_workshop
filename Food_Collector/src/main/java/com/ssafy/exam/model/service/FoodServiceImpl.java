package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exam.external.api.AllergyCollector;
import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dao.FoodDao;
import com.ssafy.exam.model.dto.Food;

@Service
public class FoodServiceImpl implements FoodService {

    private final FoodDao foodDao;

    public FoodServiceImpl(FoodDao foodDao) {
        this.foodDao = foodDao;
    }

    @Override
    public void saveFood(Food food) {
        foodDao.insertFood(food);
    }

    @Override
    public Food getFoodByCode(String foodCode) {
        return foodDao.selectFoodByCode(foodCode);
    }

    @Override
    public List<Food> getAllFoods() {
        return foodDao.selectAllFoods();
    }
}