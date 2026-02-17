package com.cfs.WeatherApp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.cfs.WeatherApp.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class Controller {
	@Autowired
	private WeatherService service;

	@GetMapping("/{city}")
	public String getWeatherData(@PathVariable String city)
	{
		return service.test();
	}
}
