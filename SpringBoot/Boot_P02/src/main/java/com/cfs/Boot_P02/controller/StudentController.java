package com.cfs.Boot_P02.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@GetMapping("/welcome")
	public String dataFetchFromDB(){

		return  "You are welcome..";
	}
}
