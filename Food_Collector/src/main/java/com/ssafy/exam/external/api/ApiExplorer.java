package com.ssafy.exam.external.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFoodDetail;
import com.ssafy.exam.model.dto.Nutrition;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Component
public class ApiExplorer {

    private static final String SERVICE_KEY = "=BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D";

    public List<Food> fetchFoodsByName(String foodName) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/FdImage/getKoreanFoodFdImageList");
        urlBuilder.append("?" + encode("serviceKey") + "=" + SERVICE_KEY);
        urlBuilder.append("&" + encode("service_Type") + "=json");
        urlBuilder.append("&" + encode("Page_No") + "=1");
        urlBuilder.append("&" + encode("Page_Size") + "=100");
        urlBuilder.append("&" + encode("food_Name") + "=" + encode(foodName));

        String json = sendRequest(urlBuilder.toString(), "application/json");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode list = root.path("response").path("list");

        List<Food> result = new ArrayList<>();
        for (JsonNode item : list) {
            Food food = new Food();
            food.setFoodCode(item.path("fd_Code").asText());
            food.setFoodName(item.path("fd_Nm").asText());
            food.setFdName(item.path("fd_Nm").asText());
            food.setGroupName(item.path("fd_Grupp_Nm").asText());
            food.setImageUrl(item.path("image_Address").asText());
            result.add(food);
        }
        return result;
    }

    public List<MainFoodDetail> fetchDetailsByMainFoodCode(String mainFoodCode) throws Exception {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList");
        urlBuilder.append("?" + encode("serviceKey") + "=" + SERVICE_KEY);
        urlBuilder.append("&" + encode("service_Type") + "=xml");
        urlBuilder.append("&" + encode("Page_No") + "=1");
        urlBuilder.append("&" + encode("Page_Size") + "=100");
        urlBuilder.append("&" + encode("main_Food_Code") + "=" + encode(mainFoodCode));

        String xml = sendRequest(urlBuilder.toString(), "application/xml");

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(xml);
        JsonNode items = root.path("body").path("items").path("item").path("idnt_List");

        List<MainFoodDetail> details = new ArrayList<>();
        if (items.isArray()) {
            for (JsonNode item : items) {
                details.add(parseMainFoodDetail(item, mainFoodCode));
            }
        } else {
            details.add(parseMainFoodDetail(items, mainFoodCode));
        }

        return details;
    }

    private Nutrition parseNutrition(String foodCode, JsonNode item) {
        Nutrition dto = new Nutrition();
        dto.setFoodCode(foodCode);
        dto.setWeight(item.path("food_Weight").asDouble(0));
        dto.setEnergyQy(item.path("energy_Qy").asDouble(0));
        dto.setWaterQy(item.path("water_Qy").asDouble(0));
        dto.setProtQy(item.path("prot_Qy").asDouble(0));
        dto.setNtrfsQy(item.path("ntrfs_Qy").asDouble(0));
        dto.setAshsQy(item.path("ashs_Qy").asDouble(0));
        dto.setCarbohydrateQy(item.path("carbohydrate_Qy").asDouble(0));
        dto.setSugarQy(item.path("sugar_Qy").asDouble(0));
        dto.setFibtgQy(item.path("fibtg_Qy").asDouble(0));
        dto.setAat19Qy(item.path("aat19_Qy").asDouble(0));
        dto.setAae10aQy(item.path("aae10a_Qy").asDouble(0));
        dto.setAaneQy(item.path("aane_Qy").asDouble(0));
        dto.setFafrefQy(item.path("fafref_Qy").asDouble(0));
        dto.setFaessfQy(item.path("faessf_Qy").asDouble(0));
        dto.setFasatfQy(item.path("fasatf_Qy").asDouble(0));
        dto.setFamsfQy(item.path("famsf_Qy").asDouble(0));
        dto.setFapufQy(item.path("fapuf_Qy").asDouble(0));
        dto.setClciQy(item.path("clci_Qy").asDouble(0));
        dto.setIrnQy(item.path("irn_Qy").asDouble(0));
        dto.setMgQy(item.path("mg_Qy").asDouble(0));
        dto.setPhphQy(item.path("phph_Qy").asDouble(0));
        dto.setPtssQy(item.path("ptss_Qy").asDouble(0));
        dto.setNaQy(item.path("na_Qy").asDouble(0));
        dto.setZnQy(item.path("zn_Qy").asDouble(0));
        dto.setCuQy(item.path("cu_Qy").asDouble(0));
        dto.setMnQy(item.path("mn_Qy").asDouble(0));
        dto.setSeQy(item.path("se_Qy").asDouble(0));
        dto.setMoQy(item.path("mo_Qy").asDouble(0));
        dto.setIdQy(item.path("id_Qy").asDouble(0));
        dto.setRtnlQy(item.path("rtnl_Qy").asDouble(0));
        dto.setCatnQy(item.path("catn_Qy").asDouble(0));
        dto.setVitdQy(item.path("vitd_Qy").asDouble(0));
        dto.setViteQy(item.path("vite_Qy").asDouble(0));
        dto.setVitk1Qy(item.path("vitk1_Qy").asDouble(0));
        dto.setVtmnB1Qy(item.path("vtmn_B1_Qy").asDouble(0));
        dto.setVtmnB2Qy(item.path("vtmn_B2_Qy").asDouble(0));
        dto.setNacnQy(item.path("nacn_Qy").asDouble(0));
        dto.setPantacQy(item.path("pantac_Qy").asDouble(0));
        dto.setPyrxnQy(item.path("pyrxn_Qy").asDouble(0));
        dto.setBiotQy(item.path("biot_Qy").asDouble(0));
        dto.setFolQy(item.path("fol_Qy").asDouble(0));
        dto.setVitb12Qy(item.path("vitb12_Qy").asDouble(0));
        dto.setVtmnCQy(item.path("vtmn_C_Qy").asDouble(0));
        dto.setCholeQy(item.path("chole_Qy").asDouble(0));
        dto.setNaclQy(item.path("nacl_Qy").asDouble(0));
        dto.setRefQy(item.path("ref_Qy").asDouble(0));
        return dto;
    }
    
    
    public List<Nutrition> fetchNutrientsByFdCode(String foodCode) throws IOException {
        List<Nutrition> result = new ArrayList<>();

        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList");
        urlBuilder.append("?serviceKey=YOUR_ENCODED_KEY"); // 서비스키는 너가 관리하던 대로
        urlBuilder.append("&service_Type=xml");
        urlBuilder.append("&Page_No=1");
        urlBuilder.append("&Page_Size=100");
        urlBuilder.append("&food_Code=" + URLEncoder.encode(foodCode, "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/xml");

        BufferedReader rd = (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300)
            ? new BufferedReader(new InputStreamReader(conn.getInputStream()))
            : new BufferedReader(new InputStreamReader(conn.getErrorStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        XmlMapper xmlMapper = new XmlMapper();
        JsonNode root = xmlMapper.readTree(sb.toString());
        JsonNode items = root.path("body").path("items").path("item").path("idnt_List");

        if (items.isArray()) {
            for (JsonNode item : items) {
                result.add(parseNutrition(foodCode, item));
            }
        } else {
            result.add(parseNutrition(foodCode, items));
        }

        return result;
    }

    

    private MainFoodDetail parseMainFoodDetail(JsonNode node, String mainFoodCode) {
        MainFoodDetail detail = new MainFoodDetail();
        detail.setMainFoodCode(mainFoodCode);
        detail.setFoodCode(node.path("food_Code").asText());
        detail.setFoodWeight(node.path("food_Weight").asDouble(0));
        return detail;
    }

    private String sendRequest(String urlStr, String contentType) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-Type", contentType);

        BufferedReader br = new BufferedReader(new InputStreamReader(
                conn.getResponseCode() >= 200 && conn.getResponseCode() <= 299
                        ? conn.getInputStream() : conn.getErrorStream()));

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) sb.append(line);
        br.close();
        conn.disconnect();
        return sb.toString();
    }

    private String encode(String param) throws Exception {
        return URLEncoder.encode(param, "UTF-8");
    }
} 