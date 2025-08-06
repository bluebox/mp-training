package com.example.MultiBeanHandlingUsingBeanName;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages="com.example.MultiBeanHandlingUsingBeanName")
public class NotificationConfig {
@Bean(value="emailNotification")
public Notification emailNotification()
{
	return  new Notification("This is email  notification");
}
@Bean(name="appNotification")
public Notification appNotification()
{
	return  new Notification("This is app  notification");
}
@Bean(name="textNotification")
public Notification textNotification()
{
	return  new Notification("This is text  notification");
}
	
}
