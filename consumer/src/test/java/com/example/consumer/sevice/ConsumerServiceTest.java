package com.example.consumer.sevice;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.common.constants.Operation;
import com.example.common.exception.DownStreamException;
import com.example.consumer.service.ConsumerService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
public class ConsumerServiceTest {
	private ObjectMapper mapper = new ObjectMapper();

	private final RestTemplate restTemplate = mock(RestTemplate.class);
	@Test
	public void getResultTest() {
		String result = "{\"result\": \"-9\"}";
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.OK);
		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.eq(String.class))).thenReturn(response);
		ConsumerService comsumerService = new ConsumerService("", mapper, restTemplate);
		Assert.assertEquals("-9", comsumerService.getResult("", Operation.ADD, "").getResult());
		;
	}
    @Test(expected = DownStreamException.class)
	public void testServiceDown() {
		String result = "{\"errorCode\": \"4003\", \"userMessage\": \"Invalid Operation\"}";
		ResponseEntity<String> response = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		when(restTemplate.exchange(Mockito.anyString(), Mockito.eq(HttpMethod.GET), Mockito.any(HttpEntity.class),
				Mockito.eq(String.class))).thenReturn(response);
		ConsumerService comsumerService = new ConsumerService("", mapper, restTemplate);
		comsumerService.getResult("", Operation.ADD, "");
	}

}
