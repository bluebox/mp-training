package com.springexamples.BeansPostConstruct2;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.Books;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) throws SQLException {
    	
    	ApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Books books=context.getBean(Books.class);
    	ResultSet result=books.getALLbooks();
    	while(result.next()) {
    		System.out.println("Book id : "+result.getInt(1)+"\n------- title : "+result.getString(2)+"\n------- Author : "+result.getString(3));
    	}
    }
}
