package com.example.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;
import com.example.common.model.Result;
import com.example.common.util.Util;

import lombok.extern.slf4j.Slf4j;

/**
 * @author aanto19
 * This class is used for calculation
 */
@Service
@Slf4j
public class ProducerService {

	/**
	 * This method is used calculation
	 * @param inputs
	 * @param operation
	 * @return {@link BigDecimal}
	 */
	private BigDecimal calculate(String inputs, Operation operation) {
		List<BigDecimal> inp = Util.convertToBigDecimal(inputs.split(GeneralConstants.COMMA));
		BigDecimal result = null;
		switch (operation) {
		case ADD:
			result =  inp.stream().reduce(BigDecimal::add).get();
			break;
		case MULTIPLY:
			result =  inp.stream().reduce(BigDecimal::multiply).get();
			break;
		case DIVIDE:
			MathContext mc = new MathContext(10, RoundingMode.HALF_UP);
			result =  inp.stream().reduce((a,b)->a.divide(b, mc)).get();
			break;
		case SUBTRACT:
			result = inp.stream().reduce(BigDecimal::subtract).get();
			break;
		}
		return result;
		

	}
	/**
	 * This method is used to call the calculate method and wrap the output in {@link Result}
	 * @param inputs
	 * @param operation
	 * @param correlationId
	 * @return {@link Result}
	 */
	public Result calculate(String inputs, Operation operation, String correlationId) {
		log.info(" inside calculate" );
		Result result = new Result(calculate(inputs, operation).toString());
		log.info("exiting calculate");
		return result;
	}
}
