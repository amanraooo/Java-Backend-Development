package com.cfs.Boot_P02.service;

import com.cfs.Boot_P02.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	@Autowired
	StudentRepo studentRepo;

	public String getStudentData(){
	return studentRepo.getStudentData();
	}
}
