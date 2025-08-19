package com.example.web.Aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.web.exceptions.DBConstrainsException;
import com.example.web.exceptions.IdNotExistException;
import com.example.web.exceptions.ValidationException;
import com.example.web.model.Book;
import com.example.web.model.Issue_Records;
import com.example.web.model.Member;
import com.example.web.utils.ValidatorsUtil;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class ValidationAspect {
	@Autowired 
	ValidatorsUtil validatorsUtil;

    @Before("@annotation(com.example.web.annotations.ValidateBooks) && args(book)")
    public void validateBook(JoinPoint joinPoint, Book book) throws Exception {
        System.out.println("AOP: Validating book before method execution...");
        try {
        	validatorsUtil.validateBook(book);
        } catch (DBConstrainsException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }
    
    @Before("@annotation(com.example.web.annotations.ValidateMembers) && args(member)")
    public void validateMember(JoinPoint joinPoint, Member member) throws Exception {
        System.out.println("AOP: Validating member before method execution...");
        try {
        	validatorsUtil.validateMember(member);
        } catch (IllegalArgumentException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }

    @Before("@annotation(com.example.web.annotations.ValidateIssueRecords) && args(issueRecord)")
    public void validateIssueRecord(JoinPoint joinPoint, Issue_Records issueRecord) throws Exception {
        System.out.println("AOP: Validating issue record before method execution...");
        try {
        	validatorsUtil.validateIssueRecord(issueRecord);
        } catch (IdNotExistException ex) {
            throw new ValidationException(ex.getMessage());
        }
    }
    
    @AfterThrowing(
            pointcut = "@annotation(com.example.web.annotations.ValidateBooks) || " +
                       "@annotation(com.example.web.annotations.ValidateMembers) || " +
                       "@annotation(com.example.web.annotations.ValidateIssueRecords)",
            throwing = "ex"
        )
        public void afterValidationThrows(JoinPoint joinPoint, Exception ex) {
            log.error("Validation failed for method: " + joinPoint.getSignature().toShortString());
            log.error("Validation error: " + ex.getMessage());
            throw new ValidationException(ex.getMessage());
        }
}