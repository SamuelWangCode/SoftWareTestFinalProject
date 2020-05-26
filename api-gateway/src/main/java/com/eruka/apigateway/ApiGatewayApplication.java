package com.eruka.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
@ComponentScan("com.eruka")
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Component
	@Primary
	class DocumentationConfig implements SwaggerResourcesProvider {

		@Override
		public List<SwaggerResource> get() {
			List resources = new ArrayList();
            resources.add(swaggerResource("注册和登陆服务Api","/loginAndlogon-producer/v2/api-docs"));
			resources.add(swaggerResource("查询战绩服务Api","/searchSummoner-producer/v2/api-docs"));


			resources.add(swaggerResource("查询英雄服务Api","/searchChampion-producer/v2/api-docs"));
			resources.add(swaggerResource("管理个人信息服务Api","/manageInformation-producer/v2/api-docs"));

			resources.add(swaggerResource("查询帖子服务Api","/guide-producer/v2/api-docs"));

			return resources;
		}

		private SwaggerResource swaggerResource(String name, String location) {
			SwaggerResource swaggerResource = new SwaggerResource();
			swaggerResource.setName(name);
			swaggerResource.setLocation(location);
			swaggerResource.setSwaggerVersion("2.0");
			return swaggerResource;
		}
	}

}
