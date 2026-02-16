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

//	another way of doing put mapping
	@PutMapping("/{id}")
	public Student updateStudent(@PathVariable Long id, @RequestBody Student student){

		Student s = studentRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found"));

		s.setName(student.getName());
		s.setEmail(student.getEmail());

		return studentRepo.save(s);
	}

	//	another way of doing patch mapping
	@PatchMapping("/{id}")
	public Student updateStudentName(@PathVariable Long id, @RequestParam String name){

		Student s = studentRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found"));

		s.setName(name);

		return s;
	}

	@PatchMapping("/{id}")
	public Student updateStudentEmail(@PathVariable Long id, @RequestParam String email){

		Student s = studentRepo.findById(id)
				.orElseThrow(()-> new RuntimeException("Student not found"));

		s.setEmail(email);

		return s;
	}

}
