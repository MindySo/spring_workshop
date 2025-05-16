package com.ssafy.exam.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;

@Repository
public interface MainFoodDao {
    void insertMainFood(MainFood mainFood); // main_food 저장
    void insertMainFoodDetail(MainFoodDetail detail); // 재료 매핑 저장

    MainFood selectMainFoodByCode(String mainFoodCode);
    List<MainFoodDetail> selectDetailByMainFoodCode(String mainFoodCode);
    
    void updateMainFoodDetail(MainFoodDetail detail); // 영양정보 저장
    MainFoodDetail selectDetail(@Param("mainFoodCode") String mainFoodCode, @Param("foodCode") String foodCode);

//    List<MainFoodDetail> selectByFoodCode(String foodCode); // 특정 food_code에 대한 영양정보 조회
}