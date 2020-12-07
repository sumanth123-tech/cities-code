package com.challenge.cities.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.cities.CitiesApplication;
import com.challenge.cities.constants.CitiesApplicationConstants;
import com.challenge.cities.exception.ServiceException;
import com.challenge.cities.service.CitiesService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class CitiesController {
	
	/**Logger**/
	private static final Logger LOG = LoggerFactory.getLogger(CitiesApplication.class);
	
	@Autowired
	CitiesService citiesService;
	
	@GetMapping("/connected")
	@ApiOperation(
	        value = "Checks if a road exists between the two cities and returns a response 'yes' or 'no' with HTTP 200 accordingly",
	        notes = "Returns HTTP 503 if the data is unavailable",
	        response = String.class 
	)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = CitiesApplicationConstants.ROAD_EXISTS_MESSAGE),
	        @ApiResponse(code = 503, message=CitiesApplicationConstants.DATA_UNAVAILABLE)
	})
	public ResponseEntity<String> areCitiesConnected(@RequestParam("origin") String origin, 
			@RequestParam("destination") String destination){
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)){
			return new ResponseEntity<String>(CitiesApplicationConstants.MANDATORY_FIELDS_NOT_SET,HttpStatus.BAD_REQUEST);
		}
		try{
			LOG.info("Checking if the road exists between "+origin+" and "+destination);
			boolean roadExists= citiesService.areCitiesConnected(origin, destination);
			if(roadExists){
				LOG.info("Road exists");
				return new ResponseEntity<String>(CitiesApplicationConstants.ROAD_EXISTS_MESSAGE,HttpStatus.OK);
			} else {
				LOG.info("No road exists between the given origin and destination");
				return new ResponseEntity<String>(CitiesApplicationConstants.ROAD_DOES_NOT_EXISTS_MESSAGE,HttpStatus.OK);
			}
		} catch(ServiceException e){
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.SERVICE_UNAVAILABLE);
		}
		
	}

}
