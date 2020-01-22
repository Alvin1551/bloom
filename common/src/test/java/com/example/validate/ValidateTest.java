package com.example.validate;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.example.common.constants.Operation;
import com.example.common.exception.InvalidInputException;
import com.example.common.validate.Validate;

public class ValidateTest {
	@Test(expected = InvalidInputException.class)
	public void testDvisionByZero() {
		Validate.validateInputs("1,0", Operation.DIVIDE);
	}
	@Test(expected = InvalidInputException.class)
	public void testInputsWithCharacter() {
		Validate.validateInputs("1,2,2a", Operation.DIVIDE);
	}
	@Test
	public void testInputs() {
		assertEquals(true, Validate.validateInputs("1,2,2", Operation.ADD));
		assertEquals(true, Validate.validateInputs("1,2,2", Operation.DIVIDE));
		assertEquals(true, Validate.validateInputs("1,2,2", Operation.MULTIPLY));
		assertEquals(true, Validate.validateInputs("1,2,2", Operation.SUBTRACT));
		assertEquals(true, Validate.validateInputs("+1,2.0,-12", Operation.SUBTRACT));
		assertEquals(true, Validate.validateInputs("0,2.0,-12", Operation.SUBTRACT));




	}

}
