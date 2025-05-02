package com.ssafy.exam.external.api;

import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.stereotype.Component;
import org.w3c.dom.*;
import org.xml.sax.InputSource;

import javax.xml.parsers.*;

@Component
public class AllergyCollector {
    public void getAllergic() throws Exception {
        // ▶ 1. API 요청 URL 구성
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/FdImage/getKoreanFoodFdImageList");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("service_Type","UTF-8") + "=xml");
        urlBuilder.append("&" + URLEncoder.encode("Page_No","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("Page_Size","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("food_Name","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); // [수정] 동적 파라미터

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd = new BufferedReader(new InputStreamReader(
                conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300
                        ? conn.getInputStream()
                        : conn.getErrorStream()
        ));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) sb.append(line);
        rd.close(); conn.disconnect();
        System.out.println(sb.toString());
        
        // ▶ 2. XML 파싱
        InputSource is = new InputSource(new StringReader(sb.toString()));
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(is);
        doc.getDocumentElement().normalize();

        NodeList foodNodes = doc.getElementsByTagName("food");
        System.out.println("foodNodes : " + foodNodes.getLength());

        Set<String> allergySet = new HashSet<>();

        for (int i = 0; i < foodNodes.getLength(); i++) {
            Node foodNode = foodNodes.item(i);
            if (foodNode.getNodeType() == Node.ELEMENT_NODE) {
                Element foodElement = (Element) foodNode;
                String allergy = getTagValue("allrgy_Info", foodElement);
                if (allergy != null && !allergy.equalsIgnoreCase("null") && !allergy.trim().isEmpty()) {
                    for (String a : allergy.split(",")) {
                        allergySet.add(a.trim());
                    }
                }
            }
        }

        // ▶ 4. 결과 출력
        System.out.println("✅ 전체 알러지 정보 목록 (" + allergySet.size() + "종류):");
        allergySet.stream().sorted().forEach(System.out::println);
    }
    
    private static String getTagValue(String tag, Element element) {
        NodeList list = element.getElementsByTagName(tag);
        if (list.getLength() == 0) return null;
        Node node = list.item(0);
        return node.getTextContent();
    }
}
