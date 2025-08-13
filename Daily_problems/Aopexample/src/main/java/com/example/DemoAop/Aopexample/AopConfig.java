package com.example.DemoAop.Aopexample;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages="com.example.DemoAop.Aopexample")
@EnableAspectJAutoProxy
public class AopConfig {

	
}
