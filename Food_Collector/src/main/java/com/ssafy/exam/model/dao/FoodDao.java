package com.ssafy.exam.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.Food;

@Repository
public interface FoodDao {
    void insertFood(Food food); // 음식 정보 저장
    Food selectFoodByCode(String foodCode); // food_code로 조회
    List<Food> selectAllFoods(); // 전체 음식 조회
}
