package com.cfs.JPA_P01.controller;

import com.cfs.JPA_P01.entity.Student;
import com.cfs.JPA_P01.repo.StudentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
//	@PatchMapping("/update-name")
//	public Student updateStudentName(@RequestParam Long id, @RequestParam String name){
//
//		Student s = studentRepo.findById(id)
//				.orElseThrow(()-> new RuntimeException("Student not found"));
//
//		s.setName(name);
//
//		return studentRepo.save(s);
//	}
//
//	@PatchMapping("/update-email")
//	public Student updateStudentEmail(@RequestParam Long id, @RequestParam String email){
//
//		Student s = studentRepo.findById(id)
//				.orElseThrow(()-> new RuntimeException("Student not found"));
//
//		s.setEmail(email);
//
//		return studentRepo.save(s);
//	}

	//using patch mapping and handeling all fields in one method
	@PatchMapping("/update")
	public Student updateStudentPatch(@RequestParam Long id, @RequestParam (required=false) String name,@RequestParam (required=false) String email){

		Student s = studentRepo.findById(id)
				.orElseThrow(() ->
						new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not found"));


		if(name!=null){
			s.setName(name);
		}
		if(email!=null){
			s.setEmail(email);
		}

		return studentRepo.save(s);
	}

}
