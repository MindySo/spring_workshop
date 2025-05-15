package com.ssafy.exam.model.dto;

public class MainFood {
    private String mainFoodCode;
    private String mainFoodName;
    private String upperGroup;
    private String subGroup;
    private double mainFoodWeight;

    public MainFood() {}

    public String getMainFoodCode() {
        return mainFoodCode;
    }

    public void setMainFoodCode(String mainFoodCode) {
        this.mainFoodCode = mainFoodCode;
    }

    public String getMainFoodName() {
        return mainFoodName;
    }

    public void setMainFoodName(String mainFoodName) {
        this.mainFoodName = mainFoodName;
    }

    public String getUpperGroup() {
        return upperGroup;
    }

    public void setUpperGroup(String upperGroup) {
        this.upperGroup = upperGroup;
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup;
    }

    public double getMainFoodWeight() {
        return mainFoodWeight;
    }

    public void setMainFoodWeight(double mainFoodWeight) {
        this.mainFoodWeight = mainFoodWeight;
    }
}
