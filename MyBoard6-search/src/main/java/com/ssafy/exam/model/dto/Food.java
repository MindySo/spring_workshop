package com.ssafy.exam.model.dto;

import java.util.List;

public class Food {
	private String fdCode;
    private String upperFdGroupNm;
    private String fdGroupNm;
    private String fdNm;
    private double fdWgh;
    private int foodCnt;
    private String imageAddress;
    private List<FoodNutri> foodNutri;
    
	public String getFdCode() {
		return fdCode;
	}
	public void setFdCode(String fdCode) {
		this.fdCode = fdCode;
	}
	public String getUpperFdGroupNm() {
		return upperFdGroupNm;
	}
	public void setUpperFdGroupNm(String upperFdGroupNm) {
		this.upperFdGroupNm = upperFdGroupNm;
	}
	public String getFdGroupNm() {
		return fdGroupNm;
	}
	public void setFdGroupNm(String fdGroupNm) {
		this.fdGroupNm = fdGroupNm;
	}
	public String getFdNm() {
		return fdNm;
	}
	public void setFdNm(String fdNm) {
		this.fdNm = fdNm;
	}
	public double getFdWgh() {
		return fdWgh;
	}
	public void setFdWgh(double fdWgh) {
		this.fdWgh = fdWgh;
	}
	public int getFoodCnt() {
		return foodCnt;
	}
	public void setFoodCnt(int foodCnt) {
		this.foodCnt = foodCnt;
	}
	public String getImageAddress() {
		return imageAddress;
	}
	public void setImageAddress(String imageAddress) {
		this.imageAddress = imageAddress;
	}
	
	public List<FoodNutri> getFoodNutri() {
		return foodNutri;
	}
	public void setFoodNutri(List<FoodNutri> foodNutri) {
		this.foodNutri = foodNutri;
	}
	
	@Override
	public String toString() {
		return "Food [fdCode=" + fdCode + ", upperFdGroupNm=" + upperFdGroupNm + ", fdGroupNm=" + fdGroupNm + ", fdNm="
				+ fdNm + ", fdWgh=" + fdWgh + ", foodCnt=" + foodCnt + ", imageAddress=" + imageAddress + ", foodNutri="
				+ foodNutri + "]";
	}
}