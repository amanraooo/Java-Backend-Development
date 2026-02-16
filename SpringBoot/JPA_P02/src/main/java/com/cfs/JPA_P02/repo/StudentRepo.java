package com.cfs.JPA_P02.repo;

import com.cfs.JPA_P02.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student,Long> {
}
