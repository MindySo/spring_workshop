package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.exam.external.api.ApiExplorer;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.FoodNutri;

@Service
public class FoodNutriServiceImpl implements FoodNutriService{

	private final ApiExplorer apiExplorer;
	public FoodNutriServiceImpl(ApiExplorer apiExplorer) {
		this.apiExplorer = apiExplorer;
	}
	
	@Override
	public List<FoodNutri> getFoodNutriList(String fdCode) throws IOException {
		return apiExplorer.fetchNutrientsByFdCode(fdCode);
	}

}
