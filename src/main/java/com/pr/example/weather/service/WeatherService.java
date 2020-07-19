package com.pr.example.weather.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pr.example.weather.payload.WeatherMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Prakash Rathod
 * 
 */
@Service
@Slf4j
public class WeatherService {

	private final RestTemplate restTemplate;
	private final String appID;
	private final String appURL = "http://api.openweathermap.org/data/2.5/weather";

	public WeatherService(RestTemplateBuilder restTemplateBuilder, @Value("${open.weather.appid}") final String appId) {
		this.restTemplate = restTemplateBuilder.build();
		this.appID = appId;
	}

	@Cacheable(cacheNames = "weather", key = "#cityCountry")
	public WeatherMapper fetchWeatherByCity(String city) throws RestClientException {
		log.info("Sync : Looking up " + city);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(appURL)
		        .queryParam("q", city)
		        .queryParam("appid", appID);
		return restTemplate.getForObject(builder.toUriString(), WeatherMapper.class);
	}

	@Cacheable(cacheNames = "weather", key = "#cityCountryTime")
	public WeatherMapper fetchWeatherByCityAndTime(String cityCountry, Long startTime, Long endTime) throws RestClientException {
		log.info("Sync : Looking up " + cityCountry);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(appURL)
		        .queryParam("q", cityCountry)
		        .queryParam("appid", appID)
		        .queryParam("type", "hour")
		        .queryParam("start", startTime)
		        .queryParam("end", endTime);
		return restTemplate.getForObject(builder.toUriString(), WeatherMapper.class);
	}
}
