package com.example.producer.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.common.constants.Operation;
import com.example.service.ProducerService;

@RunWith(MockitoJUnitRunner.class)
public class ProducerServiceTest {
	ProducerService produceService = new ProducerService();
	@Test
	public void calcTest() {
		Assert.assertEquals(produceService.calculate("1,2,3,", Operation.ADD, "").getResult(), "6");
		Assert.assertEquals(produceService.calculate("1,2,3,4", Operation.MULTIPLY, "").getResult(), "24");
		Assert.assertEquals(produceService.calculate("4,2", Operation.DIVIDE, "").getResult(), "2");
		Assert.assertEquals(produceService.calculate("10,2", Operation.SUBTRACT, "").getResult(), "8");
		Assert.assertEquals(produceService.calculate("0,2", Operation.DIVIDE, "").getResult(), "0");
	}

}
