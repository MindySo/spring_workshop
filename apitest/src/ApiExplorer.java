
// 식단관리(메뉴젠) 음식(음식코드, 음식 대분류, 음식 중분류, 음식명, 중량, 재료 수), 이미지 정보를 제공하며, 음식명으로 조회

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class ApiExplorer {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/FdImage/getKoreanFoodFdImageList"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("service_Type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 과 json 형식 지원*/
        urlBuilder.append("&" + URLEncoder.encode("Page_No","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("Page_Size","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("food_Name","UTF-8") + "=" + URLEncoder.encode("밥", "UTF-8")); /*음식 명 (검색어 입력값 포함 검색)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
        System.out.println(sb.toString());
    }
}