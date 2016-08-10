package com.ubn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class CekServerWebApplication {

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
		@Override
		public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				        .allowedOrigins("*")
				        .allowedMethods("GET", "POST", "HEAD", "OPTION")
						.allowCredentials(false).maxAge(3600);
		}
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CekServerWebApplication.class, args);
	}
}
