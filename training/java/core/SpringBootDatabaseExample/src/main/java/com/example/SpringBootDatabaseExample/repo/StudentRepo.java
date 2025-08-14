package com.example.SpringBootDatabaseExample.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SpringBootDatabaseExample.entity.Student;

public interface StudentRepo extends JpaRepository<Student, Integer> {

}
