package com.challenge.cities.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.challenge.cities.exception.ServiceException;
import com.challenge.cities.service.CitiesService;

@SpringBootTest
public class CitiesServiceImplTest {
	
	@Autowired CitiesService service;
	
	@Test
	public void testService_WhenRoadExists() throws ServiceException{
		String origin="boston",destination="newark";
		Assertions.assertTrue(service.areCitiesConnected(origin,destination));
	}
	
	@Test
	public void testService_WhenRoad_DoesNotExist() throws ServiceException{
		String origin="trenton",destination="boston";
		Assertions.assertFalse(service.areCitiesConnected(origin,destination));
	}
	
	
}
