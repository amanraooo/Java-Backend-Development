package com.cfs.JPA_P01.controller;

import com.cfs.JPA_P01.entity.Student;
import com.cfs.JPA_P01.repo.StudentRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentRepo studentRepo;

	public StudentController(StudentRepo studentRepo){
		this.studentRepo = studentRepo;
	}

	@PostMapping
	public Student createStudent(@RequestBody Student student){
		return studentRepo.save(student);
	}

	@GetMapping
	public List<Student> getAllStudent(){
		return studentRepo.findAll();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id){
		return studentRepo.findById(id).orElse(null);
	}

	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody ){
		return studentRepo.findById(id).orElse(null);
	}

}
