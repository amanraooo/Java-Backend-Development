package com.cfs.JPA_P02.entity;

import com.cfs.JPA_P02.entity.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Teacher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "teacher")
	private List<Student> student=new ArrayList<>();
}