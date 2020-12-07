package com.challenge.cities.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.challenge.cities.constants.CitiesApplicationConstants;

@SpringBootTest
public class CitiesControllerTest {

	@Autowired
	private CitiesController controller;

	@Test
	public void testResponseDirectRouteExists() throws Exception {
		String origin = "boston", destination = "New York";
		ResponseEntity<String> entity = controller.areCitiesConnected(origin, destination);
		Assertions.assertEquals(200, entity.getStatusCodeValue());
	}

	@Test
	public void testResponseSeriesOfRouteExists() throws Exception {
		String origin = "Boston", destination = "Philadelphia";
		ResponseEntity<String> entity = controller.areCitiesConnected(origin, destination);
		Assertions.assertEquals(CitiesApplicationConstants.ROAD_EXISTS_MESSAGE, entity.getBody());
	}

	@Test
	public void testResponseNoRoute() throws Exception {
		String origin = "Texas", destination = "New York";
		ResponseEntity<String> entity = controller.areCitiesConnected(origin, destination);
		Assertions.assertEquals(CitiesApplicationConstants.ROAD_DOES_NOT_EXISTS_MESSAGE, entity.getBody());
	}
	
	@Test
	public void testResponseInvalidInput() throws Exception {
		String origin = "1234", destination = "New York";
		ResponseEntity<String> entity = controller.areCitiesConnected(origin, destination);
		Assertions.assertEquals(CitiesApplicationConstants.ROAD_DOES_NOT_EXISTS_MESSAGE, entity.getBody());
	}
	
	@Test
	public void testResponseEmptyInput() throws Exception {
		String origin = "", destination = "Philadelphia";
		ResponseEntity<String> entity = controller.areCitiesConnected(origin, destination);
		Assertions.assertEquals(CitiesApplicationConstants.MANDATORY_FIELDS_NOT_SET, entity.getBody());
	}

}
