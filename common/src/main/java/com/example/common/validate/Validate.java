package com.example.common.validate;

import static com.example.common.constants.GeneralConstants.COMMA;
import static com.example.common.constants.GeneralConstants.ERR_4001;
import static com.example.common.constants.GeneralConstants.ERR_4001_MSG;
import static com.example.common.constants.GeneralConstants.ERR_4002;
import static com.example.common.constants.GeneralConstants.ERR_4002_MSG;

import com.example.common.constants.Operation;
import com.example.common.exception.InvalidInputException;
import com.example.common.model.ConsumerError;
import com.example.common.util.Util;

public class Validate {
	
	/**
	 * This method is used to validate the inputs
	 * @param inputs
	 * @param operation
	 */
	public static boolean validateInputs(String inputs,Operation operation) {
		
		String[] strArray = inputs.split(COMMA);
		if (strArray.length == 1 || !Util.isValidNumber(strArray)) {
			throw new InvalidInputException(new ConsumerError(ERR_4001, ERR_4001_MSG));
		} else if (operation == Operation.DIVIDE) {
			for (int i = 1; i < strArray.length; i++) {
				if (Integer.parseInt(strArray[i]) == 0) {
					throw new InvalidInputException(new ConsumerError(ERR_4002, ERR_4002_MSG));
				}
			}
		}
		return true;
		
	}
}
