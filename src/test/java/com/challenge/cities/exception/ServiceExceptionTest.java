package com.challenge.cities.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServiceExceptionTest {

	@Test
	public void testNoArgs() {

		ServiceException exception = new ServiceException();
		Assertions.assertNotNull(exception);

	}

	@Test
	public void testOneArgs() {

		ServiceException exception = new ServiceException("test");
		Assertions.assertNotNull(exception);

	}

	@Test
	public void testTwoArgs() {

		ServiceException exception = new ServiceException("test", "test1");
		Assertions.assertNotNull(exception);

	}
}
