package annotations.project;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

@Component
public class vehex4 {
	

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@PostConstruct
	public void initialize() {
		this.name ="shinee";
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Destroying vechile bean ");
	}
	public void printHello() {
		System.out.println("printing hello from component Vehicle bean");
	}

}
