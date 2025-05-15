package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;

public interface MainFoodService {
    void saveMainFoodWithDetails(MainFood mainFood, List<MainFoodDetail> details);
    MainFood getMainFoodByCode(String code);
	void fetchAndSaveMainFoodByName(String foodName) throws Exception;
}
