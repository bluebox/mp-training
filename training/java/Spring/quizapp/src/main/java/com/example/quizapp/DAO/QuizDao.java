package com.example.quizapp.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.quizapp.model.Quiz;

@Repository
public interface QuizDao extends JpaRepository <Quiz,Integer> {

}
