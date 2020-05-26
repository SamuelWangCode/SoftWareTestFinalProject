package com.eruka.searchChampionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@EnableHystrix
@SpringBootApplication
public class SearchChampionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SearchChampionServiceApplication.class, args);
	}

}
