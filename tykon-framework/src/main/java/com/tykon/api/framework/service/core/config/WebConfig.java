package com.tykon.api.framework.service.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("http://localhost:9009", "https://admin.helloparent.in",
				"https://helloparent.in", "https://www.helloparent.in", "https://onlineclasses.helloparent.in",
				"https://console.helloparent.in", "https://parent.helloparent.in");
	}
}
