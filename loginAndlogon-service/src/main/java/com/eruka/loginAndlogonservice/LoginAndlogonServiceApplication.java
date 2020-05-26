package com.eruka.loginAndlogonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class LoginAndlogonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginAndlogonServiceApplication.class, args);
	}

}
