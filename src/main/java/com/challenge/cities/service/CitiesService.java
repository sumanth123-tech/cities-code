package com.challenge.cities.service;

import com.challenge.cities.exception.ServiceException;

public interface CitiesService {
	
	boolean areCitiesConnected(String origin, String destination) throws ServiceException;
	
}
