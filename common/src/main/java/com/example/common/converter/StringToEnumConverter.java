package com.example.common.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.common.constants.Operation;
import com.example.common.util.Util;

/**
 * @author Alvin
 * This class is used to implements the convert method for converting String to enum for {@link Operation}l
 *
 */
@Component
public class StringToEnumConverter implements Converter<String, Operation> {

	/**
	 * This method for converting String to enum for {@link Operation}
	 */
	@Override
	public Operation convert(String source) {
		return Util.convertToOperaion(source);
	}

}
