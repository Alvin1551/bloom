package com.example.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;
import com.example.common.model.Result;
import com.example.common.validate.Validate;
import com.example.consumer.service.ConsumerService;

/**
 * @author Alvin
 *This Controller class is used for request processing
 */
@RestController
@RequestMapping("/{appname}")
public class ConsumerController {
	@Autowired
	private ConsumerService consumerService;
	/**
	 * This method is used call the {@link CalculatorService} doOperaton method
	 * @param inputs
	 * @param operation
	 * @param correlationId
	 * @return Result
	 */
	@GetMapping("/operation")
	public Result doOperaton(@RequestParam(name = GeneralConstants.INPUTS,required=true) String inputs,
			@RequestParam(name = GeneralConstants.OPERATION,required=true) Operation operation,
			@RequestParam(name = GeneralConstants.CORRELATION_ID,required=true) String correlationId) {
		Validate.validateInputs(inputs, operation);
		return consumerService.getResult(inputs, operation, correlationId);
		
	}

}
