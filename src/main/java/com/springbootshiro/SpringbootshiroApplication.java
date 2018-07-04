package com.springbootshiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 项目启动类
 *
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringbootshiroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootshiroApplication.class, args);
	}
}
