package com.cfs.WeatherApp.service;
import com.cfs.WeatherApp.dto.Root;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

	@Value("${weather.api.key}")
	private String apiKey;

	@Value("${weather.api.url}")
	private String apiUrl;

	private RestTemplate template =  new RestTemplate();


	public String test(){
		return "good";
	}

	public Root getData(String city){
		String url = apiUrl + "?key="+apiKey+"&q="+city;
		Root response = template.getForObject(url,Root.class);
		return response;
	}
}
