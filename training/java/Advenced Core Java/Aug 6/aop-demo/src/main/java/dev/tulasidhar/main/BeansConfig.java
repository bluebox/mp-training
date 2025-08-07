package dev.tulasidhar.main;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"dev.tulasidhar.beans","dev.tulasidhar.aspects"})
public class BeansConfig {
	
}
