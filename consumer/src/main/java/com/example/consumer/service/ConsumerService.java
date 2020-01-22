package com.example.consumer.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;
import com.example.common.exception.DownStreamException;
import com.example.common.model.ConsumerError;
import com.example.common.model.Result;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Alvin This class is used to
 */
@Slf4j
@Service
public class ConsumerService {
	private String url;
	private ObjectMapper mapper;
	private RestTemplate restTemplate;

	public ConsumerService(@Value("${produce.url}") String url, ObjectMapper mapper, RestTemplate restTemplate) {
		super();
		this.url = url;
		this.mapper = mapper;
		this.restTemplate = restTemplate;
	}

	/**
	 * This method is used to call the producer Service
	 * 
	 * @param inputs
	 * @param operation
	 * @param correlationId
	 * @return @link Result
	 */
	public Result getResult(String inputs, Operation operation, String correlationId) {
		log.info("Inside getResult ");
		String url = String.format(this.url, inputs, operation, correlationId);
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> producerEntity = null;
		boolean isError = false;
		try {
			producerEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
			if (producerEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
				isError = true;
				log.error(producerEntity.getBody());
			}
		} catch (RestClientException e1) {
			log.error("RestClientException ", e1);
			isError = true;
		}
		if (isError) {
			throw new DownStreamException(new ConsumerError(GeneralConstants.ERR_5002, GeneralConstants.ERR_5002_MSG));
		}
		Result result = null;
		try {
			result = mapper.readValue(producerEntity.getBody(), Result.class);
		} catch (JsonProcessingException e) {
			log.error("Error while converting to ResultObject ", e);
			throw new RuntimeException(e);
		}

		log.info("Exiting getResult ");
		return result;

	}

}
