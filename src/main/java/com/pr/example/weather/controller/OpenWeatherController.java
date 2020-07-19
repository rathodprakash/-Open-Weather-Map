package com.pr.example.weather.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pr.example.weather.payload.ApiResponse;
import com.pr.example.weather.payload.WeatherMapper;
import com.pr.example.weather.service.WeatherService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * 
 * @author Prakash Rathod
 * 
 */
@RestController
@RequestMapping("/api/v1/ow")
@Api(tags = "Open Weather", value = "/ow")
public class OpenWeatherController {

	private final WeatherService weatherService;
	
	public OpenWeatherController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	@ApiOperation(value = "Get weather by city")
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
	public ApiResponse weatherForCity(@PathVariable("city") String city) {
		WeatherMapper weather = weatherService.fetchWeatherByCity(city);
		return new ApiResponse("GetKey", "View weather by city", HttpStatus.OK.value(), weather);
	}
	
	@ApiOperation(value = "Get weather by city & time")
	@RequestMapping(value = "/cityAndTime", method = RequestMethod.GET)
	public ApiResponse weatherForCityAndTime(@RequestParam String city, @RequestParam Long startTimeStamp, @RequestParam Long endTimeStamp) {
		WeatherMapper weather = weatherService.fetchWeatherByCityAndTime(city, startTimeStamp, endTimeStamp);
		return new ApiResponse("GetKey", "View weather by city & time", HttpStatus.OK.value(), weather);
	}
}
