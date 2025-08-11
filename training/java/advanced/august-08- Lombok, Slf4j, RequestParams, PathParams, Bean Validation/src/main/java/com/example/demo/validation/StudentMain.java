package com.example.demo.validation;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class StudentMain {

    public static void main(String[] args) {
        Student student = Student.builder()
                                 .name("kaushik")
                                 .age(-4)
                                 .build();

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Student>> violations = validator.validate(student);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<Student> violation : violations) {
                System.out.println(violation.getPropertyPath() + ": " + violation.getMessage());
            }
        } else {
            System.out.println("Valid Student: " + student);
        }
    }
}
