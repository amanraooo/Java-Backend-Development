package com.cfs.WeatherApp.service;
import com.cfs.WeatherApp.dto.Root;
import com.cfs.WeatherApp.dto.WeatherResponse;
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

	public WeatherResponse getData(String city){
		String url = apiUrl + "?key="+apiKey+"&q="+city;
		Root response = template.getForObject(url,Root.class);
		WeatherResponse weatherResponse = new WeatherResponse();

		weatherResponse.setCity(response.getLocation().name);
		weatherResponse.setCountry(response.getLocation().getCountry());
		weatherResponse.setRegion(response.getLocation().getRegion());

		weatherResponse.setCondition(response.getCurrent().getCondition().getText());
		weatherResponse.setTemperature(response.getCurrent().getTemp_c());

		return weatherResponse;
	}
}
