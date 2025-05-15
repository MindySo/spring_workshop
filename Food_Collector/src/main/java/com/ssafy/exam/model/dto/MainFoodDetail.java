package com.ssafy.exam.model.dto;

public class MainFoodDetail {
    private int id;
    private String mainFoodCode;
    private String foodCode;
    private double foodWeight;

    public MainFoodDetail() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMainFoodCode() {
        return mainFoodCode;
    }

    public void setMainFoodCode(String mainFoodCode) {
        this.mainFoodCode = mainFoodCode;
    }

    public String getFoodCode() {
        return foodCode;
    }

    public void setFoodCode(String foodCode) {
        this.foodCode = foodCode;
    }

    public double getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(double foodWeight) {
        this.foodWeight = foodWeight;
    }
}
