package com.springexamples.LazyAnnotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.springexamples.beans.EagerMessage;
import com.springexamples.beans.Message;
import com.springexamples.config.ProjectConfig;

public class App {
    public static void main(String[] args) {
    	ApplicationContext context=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	EagerMessage message=context.getBean(EagerMessage.class);
        Message msg=context.getBean(Message.class);
    }
}
