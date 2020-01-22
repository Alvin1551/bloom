package com.example.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.example.common.interceptor.APIInvTimeInterceptor;


/**
 * @author Alvin
 * This method is used to start the sping boot application
 *
 */
@SpringBootApplication
public class ConsumerApplication {
	@Autowired
	private APIInvTimeInterceptor apiInvTimeIntereptor;

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

	/**
	 * This method is used to configure the time out for {@link RestTemplate}
	 * @param timeout
	 * @return {@link RestTemplate}
	 */
	@Bean
	public RestTemplate getRestTemplate(@Value("${connection.time.out}") int timeout) {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
		clientHttpRequestFactory.setConnectTimeout(timeout * 1000);
		clientHttpRequestFactory.setReadTimeout(timeout * 1000);
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		 restTemplate.getInterceptors().add(apiInvTimeIntereptor);


		return restTemplate;
	}
}
