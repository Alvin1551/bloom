package com.example.consumer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.example.common.converter.StringToEnumConverter;
import com.example.common.interceptor.LoggingInterceptor;
import com.example.common.interceptor.RequestProcessingTimeInterceptor;

import lombok.extern.slf4j.Slf4j;
/**
 * This class used to add all customised configurations for Spring Boot
 * @author Alvin
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.example.common.interceptor", "com.example.common.advice"})
@Slf4j
 class ApplicationConfig extends WebMvcConfigurationSupport {
	
	
	@Autowired
    private LoggingInterceptor loggingInterceptor;
	
	@Autowired
    private RequestProcessingTimeInterceptor requestProcInterceptor;
	/**
	 *This methods is used for adding the {@link StringToEnumConverter}
	 */
	@Override
	   public FormattingConversionService mvcConversionService() {
	       FormattingConversionService f = super.mvcConversionService();
	       log.info("added StringToEnumConverter");
	       f.addConverter(new StringToEnumConverter());
	       return f;
	   }
	/**
	 *This methods is used for adding the {@link RequestInterceptor},
	 *{@link LoggingInterceptor},{@link RequestProcessingTimeInterceptor} to the {@link InterceptorRegistry}
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loggingInterceptor).addPathPatterns("/**").order(1);
		registry.addInterceptor(requestProcInterceptor).addPathPatterns("/**").order(3);

	}
}
