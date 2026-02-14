package com.cfs.Boot_P02.controller;

import com.cfs.Boot_P02.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

	@Autowired
	StudentService studentService;

	@GetMapping("/welcome")
	public String dataFetchFromDB(){

		return  studentService.getStudentData();
	}
}
