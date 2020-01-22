package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;
import com.example.common.model.Result;
import com.example.common.validate.Validate;
import com.example.service.ProducerService;

/**
 * @author Alvin This class is used for request processing and mapping
 */
@RestController
@RequestMapping("/{appname}")
public class ProducerController {
	@Autowired
	private ProducerService producer;

	/**
	 * This method is used to calculate the specific operation
	 * 
	 * @param inputs
	 * @param operation
	 * @param correlationId
	 * @return {@link Result}
	 */
	@GetMapping("/operation")
	public Result calculate(@RequestParam(name = GeneralConstants.INPUTS, required = true) String inputs,
			@RequestParam(name = GeneralConstants.OPERATION, required = true) Operation operation,
			@RequestParam(name = GeneralConstants.CORRELATION_ID, required = true) String correlationId) {
		Validate.validateInputs(inputs, operation);
		Result result = producer.calculate(inputs, operation, correlationId);
		return result;

	}

}
