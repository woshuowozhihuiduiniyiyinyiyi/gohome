package com.hj.tj.gohome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hj.tj.gohome.mapper")
public class GohomeApplication {

	public static void main(String[] args) {
		SpringApplication.run(GohomeApplication.class, args);
	}
}
