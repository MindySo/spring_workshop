package com.ssafy.exam.model.service;

import java.io.IOException;
import java.util.List;

import com.ssafy.exam.model.dto.Food;

public interface FoodService {
	List<Food> getFoodList(String name) throws IOException;
	void getAllergic() throws Exception;
}
