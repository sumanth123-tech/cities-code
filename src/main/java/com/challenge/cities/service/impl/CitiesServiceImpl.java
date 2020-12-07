package com.challenge.cities.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.challenge.cities.constants.CitiesApplicationConstants;
import com.challenge.cities.exception.ServiceException;
import com.challenge.cities.service.CitiesService;

@Service
public class CitiesServiceImpl implements CitiesService {

	/** Logger **/
	private static final Logger LOG = LogManager.getLogger(CitiesServiceImpl.class.getName());

	Map<String, String> allPossibleRoutes = new HashMap<>();

	@Override
	public boolean areCitiesConnected(String origin, String destination) throws ServiceException {
		boolean roadExists=false;
		/* Fetch the details only on first load */
		if (allPossibleRoutes.isEmpty()) {
			LOG.info("Fetching the routes");
			buildCitiesMap();
		}

		/* Check if the road exists */
		String dest = allPossibleRoutes.get(origin.toLowerCase());
		while (dest != null) {
			String[] possibleDesinations = Arrays.stream(dest.split(","))
					  .map(String::trim)
					  .toArray(String[]::new);
			roadExists= Arrays.stream(possibleDesinations).filter(possibleDestination->possibleDestination.equalsIgnoreCase(destination))
			.collect(Collectors.toList()).size()>0;
			
			if(roadExists){
				break;
			}
			
			dest = allPossibleRoutes.get(dest);
		}

		return roadExists;

	}

	private void buildCitiesMap() throws ServiceException {
		Resource resource = new ClassPathResource("data/cities.txt");
		try {
			InputStream input = resource.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(input));
			String line = null;
			while ((line = br.readLine()) != null) {
				String[] content = Arrays.stream(line.split(","))
						  .map(String::trim)
						  .map(String::toLowerCase)
						  .toArray(String[]::new);
				String source = content[0];
				String destination = content[1];
				LOG.info("Route details " + source + "->" + destination);
				/*
				 * Add an entry for all possible routes both source to
				 * destination and destination to source if an entry already
				 * exists for a source, append the new destination to the
				 * existing routes with a comma
				 */
				if (allPossibleRoutes.get(source) != null) {
					allPossibleRoutes.put(source, destination);
				} else {
					allPossibleRoutes.put(source, allPossibleRoutes.get(source) + "," + destination);
				}
				if (allPossibleRoutes.get(destination) != null) {
					allPossibleRoutes.put(destination, source);
				} else {
					allPossibleRoutes.put(destination, allPossibleRoutes.get(destination) + "," + source);
				}
			}
		} catch (IOException e) {
			LOG.info("Error fetching city details: " + e.getMessage());
			throw new ServiceException(CitiesApplicationConstants.DATA_UNAVAILABLE);
		}

	}

}
