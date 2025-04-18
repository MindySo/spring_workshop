package com.ssafy.exam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.ssafy.exam.model.service")
@SpringBootApplication
public class SSAFYFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(SSAFYFoodApplication.class, args);
	}

}
