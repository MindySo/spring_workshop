package com.ssafy.exam.config;

import java.io.File;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.exam.model.dao")
public class DBConfig {}