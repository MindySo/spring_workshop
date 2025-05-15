package com.ssafy.exam.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;

@Repository
public interface MainFoodDao {
    void insertMainFood(MainFood mainFood); // main_food 저장
    void insertMainFoodDetail(MainFoodDetail detail); // 재료 매핑 저장

    MainFood selectMainFoodByCode(String mainFoodCode);
    List<MainFoodDetail> selectDetailByMainFoodCode(String mainFoodCode);
}