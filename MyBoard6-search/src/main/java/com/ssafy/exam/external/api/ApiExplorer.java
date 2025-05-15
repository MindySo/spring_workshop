package com.ssafy.exam.external.api;

// 식단관리(메뉴젠) 음식(음식코드, 음식 대분류, 음식 중분류, 음식명, 중량, 재료 수), 이미지 정보를 제공하며, 음식명으로 조회

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.FoodNutri;

@Component
public class ApiExplorer {
    
 // [수정] 기존 main() 삭제하고, 메서드로 변경
    public List<Food> fetchFoodsByName(String foodName) throws IOException {
        List<Food> result = new ArrayList(); // [추가] 결과 리스트

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/FdImage/getKoreanFoodFdImageList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("service_Type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Page_No","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Page_Size","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("food_Name","UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8")); // [수정] 동적 파라미터

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // [추가] JSON 파싱
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(sb.toString());
        JsonNode list = root.path("response").path("list");

        for (JsonNode item : list) {
            Food food = new Food();
            food.setFdCode(item.path("fd_Code").asText());
            food.setUpperFdGroupNm(item.path("upper_Fd_Grupp_Nm").asText());
            food.setFdGroupNm(item.path("fd_Grupp_Nm").asText());
            food.setFdNm(item.path("fd_Nm").asText());
            food.setFdWgh(item.path("fd_Wgh").asDouble());
            food.setFoodCnt(item.path("food_Cnt").asInt());
            food.setImageAddress(item.path("image_Address").asText());
            result.add(food);
        }

        return result; // [수정] 결과 반환
    }
    
    // 영양정보는 xml로만 받아진다..
    public List<FoodNutri> fetchNutrientsByFdCode(String fdCode) throws IOException {
        List<FoodNutri> result = new ArrayList(); // [추가] 결과 리스트

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("service_Type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Page_No","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Page_Size","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
//        System.out.println(fdCode);
        urlBuilder.append("&" + URLEncoder.encode("food_Code","UTF-8") + "=" + URLEncoder.encode(fdCode, "UTF-8")); // [수정] 동적 파라미터

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        // [추가] JSON 파싱
        String xml = sb.toString();
//        System.out.println("응답 XML:\n" + xml);

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(xml);

        // /response/body/items/item/idnt_List
        JsonNode items = root.path("body").path("items").path("item").path("idnt_List");

        // idnt_List가 배열이 아닐 수도 있음 → 단일 객체 처리
        if (items.isArray()) {
            for (JsonNode item : items) {
                result.add(parseFoodNutri(item));
            }
        } else {
            result.add(parseFoodNutri(items));
        }

//        System.out.println(items);
        
        return result;
    }

    private FoodNutri parseFoodNutri(JsonNode item) {
        FoodNutri dto = new FoodNutri();
        dto.setEnergy_Qy(item.path("energy_Qy").asDouble(0));
        dto.setProt_Qy(item.path("prot_Qy").asDouble(0));
        dto.setNtrfs_Qy(item.path("ntrfs_Qy").asDouble(0));
        dto.setCarbohydrate_Qy(item.path("carbohydrate_Qy").asDouble(0));
        dto.setNa_Qy(item.path("na_Qy").asDouble(0));
        dto.setPtss_Qy(item.path("ptss_Qy").asDouble(0));
        dto.setClci_Qy(item.path("clci_Qy").asDouble(0));
        dto.setIrn_Qy(item.path("irn_Qy").asDouble(0));
        dto.setVtmn_C_Qy(item.path("vtmn_C_Qy").asDouble(0));
        dto.setVtmn_B1_Qy(item.path("vtmn_B1_Qy").asDouble(0));
        return dto;
    }
    
}