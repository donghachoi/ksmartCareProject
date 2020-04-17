package com.cafe24.choiyooq1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cafe24.choiyooq1.interceptor.CenterInterceptor;
import com.cafe24.choiyooq1.interceptor.CommonInterceptor;
import com.cafe24.choiyooq1.interceptor.LoginInterceptor;
import com.cafe24.choiyooq1.interceptor.MaterInterceptor;


@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private LoginInterceptor loginInterceptor;
	
	@Autowired
	private CommonInterceptor commonInterceptor;
	
	
	@Autowired
	private MaterInterceptor masterInterceptor;
	
	@Autowired
	private CenterInterceptor centerInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(commonInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/")
				.excludePathPatterns("/vendor/**")
				.excludePathPatterns("/assets/**");
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/login")
				.excludePathPatterns("/*")
				.excludePathPatterns("/vendor/**")
				.excludePathPatterns("/assets/**");
		
		registry.addInterceptor(masterInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/login")
				.excludePathPatterns("/*")
				.excludePathPatterns("/master/**")
				.excludePathPatterns("/vendor/**")
				.excludePathPatterns("/assets/**");
		
		registry.addInterceptor(centerInterceptor)
				.addPathPatterns("/master/**")
				.excludePathPatterns("/login")
				.excludePathPatterns("/*")
				.excludePathPatterns("/vendor/**")
				.excludePathPatterns("/assets/**");
		}
}