package com.ssafy.board.config;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisConfig {
	private static SqlSessionFactory factory;
	
	static {
		// factory 건설하기
		String resource = "mybatis-config.xml";
		try(InputStream inputStream = Resources.getResourceAsStream(resource)){
			factory = new SqlSessionFactoryBuilder().build(inputStream);
			System.out.println("공장 건설 성공");
			
		}catch(IOException e) {
			System.out.println("공장 건설 실패");
		}
		
		
		
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
