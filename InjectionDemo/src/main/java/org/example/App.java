package org.example;

public class App {
    public static void main(String[] args) {
        // manual dependency injection: is not required  because it is implicitly handled by spring
//        Student s = new Student();
//        Course course = new DSA();
//        s.setCourse(course);
//        s.study();

        Student s = new Student(new JavaFullStack());
        s.study();
    }
}