package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.Nutrition;

public interface NutritionService {
    void saveNutrition(Nutrition nutrition);
    List<Nutrition> getNutritionByFoodCode(String foodCode);
}
