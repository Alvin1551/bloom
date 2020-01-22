package com.example.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;

import com.example.common.constants.Operation;
import com.example.common.util.Util;

public class UtilTest {
	private String strArray [] = {"1","2","3"};
	@Test
	public void testBigDecimalConvertion() {
		List<BigDecimal> list = Util.convertToBigDecimal(strArray);
		assertArrayEquals(strArray, list.stream().map(BigDecimal::toString).toArray(String[]::new));
	}
	@Test
	public void isValidNumber() {
		assertEquals(Util.isValidNumber(this.strArray), true);
		String strArray [] = {"1","2","3,s"};
		assertEquals(Util.isValidNumber(strArray), false);
		String [] strArray1 = {".1","2","3"};
		assertEquals(Util.isValidNumber(strArray1), false);
		String [] strArray2 = {"1.1","2","3"};
		assertEquals(Util.isValidNumber(strArray2), true);
		String [] strArray3 = {"1.1","-2","+3"};
		assertEquals(Util.isValidNumber(strArray3), true);

	}
	@Test
	public void convertToOperaion() {
		assertEquals(Operation.ADD, Util.convertToOperaion("add"));
		assertEquals(Operation.ADD, Util.convertToOperaion("ADD"));
		assertEquals(Operation.MULTIPLY, Util.convertToOperaion("MULTIPLY"));
		assertEquals(Operation.MULTIPLY, Util.convertToOperaion("multiply"));
		assertEquals(Operation.SUBTRACT, Util.convertToOperaion("SUBTRACT"));
		assertEquals(Operation.SUBTRACT, Util.convertToOperaion("subtract"));
		assertEquals(Operation.DIVIDE, Util.convertToOperaion("DIVIDE"));
		assertEquals(Operation.DIVIDE, Util.convertToOperaion("divide"));
		assertEquals(Operation.MULTIPLY, Util.convertToOperaion(""));


	}
	@Test(expected = IllegalArgumentException.class)
	public void convertToOperaionWithException() {
		assertEquals(Operation.DIVIDE, Util.convertToOperaion("didfdfvide"));

	}
}
