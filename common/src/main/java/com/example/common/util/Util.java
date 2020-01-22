package com.example.common.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.example.common.constants.GeneralConstants;
import com.example.common.constants.Operation;

/**
 * @author Alvin
 * This class is used for all the Utility methods
 *
 */
public class Util {
	/**
	 * This method is used convert StringArray to {@link List<BigDecimal>}
	 * @param strArray
	 * @return {@link List<BigDecimal>}
	 */
	public static List<BigDecimal> convertToBigDecimal(String[] strArray) {
		return Arrays.stream(strArray).map(BigDecimal::new).collect(Collectors.toList());
	}
	/**
	 * This method is used to check the given array contains numbers 
	 * @param strArray
	 * @return boolean
	 */
	public static boolean isValidNumber(String[] strArray) {
		return Arrays.stream(strArray).filter(Util::isValidNumber).count() == strArray.length;

	}
	/**
	 * This method is used for checking the given String is a number
	 * @param number
	 * @return
	 */
	private static boolean isValidNumber(String number) {
		return number != null && !number.isEmpty() && number.matches(GeneralConstants.REGEX_NUMBER);
	}
	/**
	 * This method is used to convert String to respective Enum
	 * @param source
	 * @return {@link Operation}
	 */
	public static Operation convertToOperaion(String source) {
		Operation operation = null;
		if (source == null || source.isEmpty()) {
			source = Operation.MULTIPLY.toString();
		}
		operation = Operation.valueOf(source.toUpperCase());
		return operation;

	
	}

}
