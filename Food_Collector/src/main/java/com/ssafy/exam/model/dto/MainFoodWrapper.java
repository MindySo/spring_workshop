package com.ssafy.exam.model.dto;

import java.util.List;

public class MainFoodWrapper {
	private MainFood mainFood;
	private List<MainFoodDetail> details;
	private List<Food> foods;

	public MainFoodWrapper() {
	}

	public MainFoodWrapper(MainFood mainFood, List<MainFoodDetail> details, List<Food> foods) {
		this.mainFood = mainFood;
		this.details = details;
		this.foods = foods;
	}

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

	@Override
	public String toString() {
		return "MainFoodWrapper [mainFood=" + mainFood + ", details=" + details + ", foods=" + foods + "]";
	}
	
	
}