package com.eruka.manageInformationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class ManageInformationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageInformationServiceApplication.class, args);
	}

}
