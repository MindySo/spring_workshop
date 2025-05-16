package com.ssafy.exam.external.api;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.ssafy.exam.controller.MainFoodController.MainFoodWrapper;
import com.ssafy.exam.model.dto.Food;
import com.ssafy.exam.model.dto.MainFood;
import com.ssafy.exam.model.dto.MainFoodDetail;
import com.ssafy.exam.model.service.MainFoodService;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class ApiExplorer {
	private static final String SERVICE_KEY = "BUjKsgbwH%2F7q6l%2FTqMjSBFtncnNr8macDqAIKq0EjIZggZ7F2qFyzgbPYGIk6Hpal29R42e3jloOkitqeRsRHg%3D%3D";

	////////////////////////////// 음식 저장 ///////////////////////////////////
	public List<MainFoodWrapper> fetchMainFoodWithDetails(String foodName) throws Exception {
		System.out.println(foodName);
		List<MainFoodWrapper> wrappers = new ArrayList<>();
		int pageNo = 1;

		while (true) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1390802/AgriFood/FdFoodImage/getKoreanFoodFdFoodImageList");
			urlBuilder.append("?" + encode("serviceKey") + "=" + SERVICE_KEY);
			urlBuilder.append("&" + encode("service_Type") + "=xml");
			urlBuilder.append("&" + encode("Page_No") + "=" + pageNo);
			urlBuilder.append("&" + encode("Page_Size") + "=20");
			urlBuilder.append("&" + encode("food_Name") + "=" + encode(foodName));

			String xml = sendRequest(urlBuilder.toString(), "application/xml");
			
			if (xml.contains("LIMITED_NUMBER_OF_SERVICE_REQUESTS_EXCEEDS_ERROR")) {
			    System.out.println("요청 제한 초과! 중단 또는 일정 시간 대기 필요.");
			    System.exit(0); // 또는 sleep 후 재시도
			}
			
			XmlMapper mapper = new XmlMapper();
			JsonNode root = mapper.readTree(xml);
			JsonNode items = root.path("body").path("items").path("item");

			// 데이터 없으면 종료
			if (items.isMissingNode() || (items.isArray() && items.size() == 0)) {
				break;
			}

			if (items.isArray()) {
				for (JsonNode item : items) {
					MainFood mainFood = parseMainFood(item);
					List<Food> foods = extractFoods(item);
					List<MainFoodDetail> details = extractDetailsFromFoodList(item, mainFood.getMainFoodCode());

					// 순환 참조 없이 Wrapper 구성
					MainFoodWrapper wrapper = new MainFoodWrapper();
					wrapper.setMainFood(mainFood);
					wrapper.setFoods(foods);
					wrapper.setDetails(details);

					wrappers.add(wrapper);
				}
			} else if (!items.isMissingNode()) {
				MainFood mainFood = parseMainFood(items);
				List<Food> foods = extractFoods(items);
				List<MainFoodDetail> details = extractDetailsFromFoodList(items, mainFood.getMainFoodCode());

				MainFoodWrapper wrapper = new MainFoodWrapper();
				wrapper.setMainFood(mainFood);
				wrapper.setFoods(foods);
				wrapper.setDetails(details);

				wrappers.add(wrapper);
			} else {
				System.out.println("⚠️ item 노드가 존재하지 않음!");
			}
			pageNo++;
		}

		return wrappers;
	}

	public List<Food> fetchFoodsByName(String foodName) throws Exception {
		List<Food> result = new ArrayList<>();
		int pageNo = 1;

		while (true) {
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/1390802/AgriFood/FdFoodImage/getKoreanFoodFdFoodImageList");
			urlBuilder.append("?" + encode("serviceKey") + "=" + SERVICE_KEY);
			urlBuilder
					.append("&" + URLEncoder.encode("service_Type", "UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("Page_No", "UTF-8") + "=" + URLEncoder.encode((pageNo + ""), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("Page_Size", "UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
			urlBuilder
					.append("&" + URLEncoder.encode("food_Name", "UTF-8") + "=" + URLEncoder.encode(foodName, "UTF-8"));

			String xml = sendRequest(urlBuilder.toString(), "application/json");
			System.out.println(xml);

			// ✅ JSON 응답 → ObjectMapper 사용
			XmlMapper mapper = new XmlMapper();
			JsonNode root = mapper.readTree(xml);
			JsonNode items = root.path("body").path("items").path("item");

			// 데이터 없으면 종료
			if (items.isMissingNode() || (items.isArray() && items.size() == 0)) {
				break;
			}

			if (items.isArray()) {
				for (JsonNode item : items) {
					result.add(parseFood(item));
				}
			} else {
				result.add(parseFood(items));
			}
			pageNo++;
		}

		return result;
	}

	private MainFood parseMainFood(JsonNode item) {
		MainFood mf = new MainFood();
		mf.setMainFoodCode(item.path("fd_Code").asText());
		mf.setMainFoodName(item.path("fd_Nm").asText());
		mf.setUpperGroup(item.path("upper_Fd_Grupp_Nm").asText());
		mf.setSubGroup(item.path("fd_Grupp_Nm").asText());
		mf.setMainFoodWeight(item.path("fd_Wgh").asDouble(0));
		return mf;
	}

	private List<Food> extractFoods(JsonNode item) {
		List<Food> list = new ArrayList<>();
		JsonNode foodList = item.path("food_List").path("food");

		if (foodList.isArray()) {
			for (JsonNode node : foodList) {
				list.add(parseFood(node));
			}
		} else if (!foodList.isMissingNode()) {
			list.add(parseFood(foodList));
		}

		return list;
	}

	private Food parseFood(JsonNode node) {
		Food food = new Food();
		food.setFoodCode(node.path("food_Code").asText());
		food.setFoodName(node.path("food_Nm").asText());
		food.setGroupName(node.path("nation_Std_Food_Grupp_Code_Nm").asText());
		food.setAllergyInfo(node.path("allrgy_Info").asText());
		food.setImageUrl(node.path("food_Image_Address").asText());
		return food;
	}

	private List<MainFoodDetail> extractDetailsFromFoodList(JsonNode item, String mainFoodCode) {
		List<MainFoodDetail> details = new ArrayList<>();
		JsonNode foodList = item.path("food_List").path("food");

		if (foodList.isArray()) {
			for (JsonNode food : foodList) {
				details.add(parseDetail(food, mainFoodCode));
			}
		} else if (!foodList.isMissingNode()) {
			details.add(parseDetail(foodList, mainFoodCode));
		}

		return details;
	}

	private MainFoodDetail parseDetail(JsonNode food, String mainFoodCode) {
		MainFoodDetail detail = new MainFoodDetail();
		detail.setMainFoodCode(mainFoodCode);
		detail.setFoodCode(food.path("food_Code").asText());
		detail.setFoodWeight(food.path("food_Wgh").asDouble(0));
		return detail;
	}

	/////////////////////////////////////// 영양소 저장 //////////////////////////////////
	public List<MainFoodDetail> fetchDetailsByMainFoodCode(String mainFoodCode) throws Exception {
		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList");
		urlBuilder.append("?" + encode("serviceKey") + "=" + SERVICE_KEY);
		urlBuilder.append("&" + encode("food_Code") + "=" + URLEncoder.encode(mainFoodCode, "UTF-8"));

		String xml = sendRequest(urlBuilder.toString(), "application/xml");

		XmlMapper xmlMapper = new XmlMapper();
		JsonNode root = xmlMapper.readTree(xml);
		JsonNode items = root.path("body").path("items").path("item");
		System.out.println("item" + mainFoodCode);

		List<MainFoodDetail> details = new ArrayList<>();
		if (items.isArray()) {
		    for (JsonNode item : items) {
		        JsonNode idntListNode = item.path("idnt_List");

		        if (idntListNode.isArray()) {
		            for (JsonNode idnt : idntListNode) {
		                details.add(parseNutrition(mainFoodCode, idnt)); // ✅ 각각의 idnt만 넘기기
		            }
		        } else if (!idntListNode.isMissingNode()) {
		            details.add(parseNutrition(mainFoodCode, idntListNode));
		        }
		    }
		} else if (!items.isMissingNode()) {
		    JsonNode idntListNode = items.path("idnt_List");

		    if (idntListNode.isArray()) {
		        for (JsonNode idnt : idntListNode) {
		            details.add(parseNutrition(mainFoodCode, idnt)); // ✅ 각각의 idnt만 넘기기
		        }
		    } else if (!idntListNode.isMissingNode()) {
		        details.add(parseNutrition(mainFoodCode, idntListNode));
		    }
		}

		return details;
	}

	private MainFoodDetail parseNutrition(String MainfoodCode, JsonNode item) {
		MainFoodDetail dto = new MainFoodDetail();
		dto.setMainFoodCode(MainfoodCode);
		dto.setFoodCode(item.path("food_Code").asText());
		dto.setFoodWeight(item.path("food_Weight").asDouble(0));
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

	public List<MainFoodDetail> fetchNutrientsByFdCode(String foodCode) throws IOException {
		List<MainFoodDetail> result = new ArrayList<>();

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/1390802/AgriFood/MzenFoodNutri/getKoreanFoodIdntList");
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
				conn.getResponseCode() >= 200 && conn.getResponseCode() <= 299 ? conn.getInputStream()
						: conn.getErrorStream()));

		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null)
			sb.append(line);
		br.close();
		conn.disconnect();
		return sb.toString();
	}

	private String encode(String param) throws Exception {
		return URLEncoder.encode(param, "UTF-8");
	}
}