package com.ssafy.exam.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.Nutrition;

@Repository
public interface NutritionDao {
    void insertNutrition(Nutrition nutrition); // 영양정보 저장
    List<Nutrition> selectByFoodCode(String foodCode); // 특정 food_code에 대한 영양정보 조회
}
