package org.example;

public class Student {
	private Course course; // Course type ka object
	// dependency injection
	// setter injection

	public Student() {}

	public Student(Course course) {
		this.course = course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public void study() {
		int start = course.enroll();
		if (start >= 1) {
			System.out.println("Journey Started....");
		} else {
			System.out.println("Payment Failed");
		}
	}
}