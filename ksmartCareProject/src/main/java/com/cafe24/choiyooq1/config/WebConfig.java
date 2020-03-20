package com.cafe24.choiyooq1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.choiyooq1.interceptor.CommonInterceptor;
import com.cafe24.choiyooq1.interceptor.LoginInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/css/**");
		
		
		/*
		 * registry.addInterceptor(loginInterceptor) .addPathPatterns("/**")
		 * .excludePathPatterns("/") .excludePathPatterns("/mInsert")
		 * .excludePathPatterns("/login");
		 */
		 

	}
}
