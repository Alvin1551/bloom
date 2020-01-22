package com.example.consumer.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;
import com.example.common.interceptor.APIInvTimeInterceptor;
import com.example.common.interceptor.LoggingInterceptor;
import com.example.common.interceptor.RequestProcessingTimeInterceptor;
import com.example.consumer.service.ConsumerService;

@Import({ LoggingInterceptor.class,
		RequestProcessingTimeInterceptor.class,APIInvTimeInterceptor.class })
@WebMvcTest(value = ConsumerController.class)
@RunWith(SpringRunner.class)
public class ConsumerControllerTest {
	private String defaultUrl = "/producer/operation";
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ConsumerService consumerService;

	@Test
	public void doOperaton() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(defaultUrl).param(GeneralConstants.INPUTS, "1,1,1")
				.param(GeneralConstants.OPERATION, Operation.ADD.toString()).param(GeneralConstants.CORRELATION_ID, "3434")
				.accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder).andExpect(status().isOk());
	}
}
