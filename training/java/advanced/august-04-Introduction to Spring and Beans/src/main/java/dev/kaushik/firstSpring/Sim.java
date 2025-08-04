package dev.kaushik.firstSpring;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.stereotype.Component;

public interface Sim {
	void calling(); 
	void data();
}

@Component
class Airtel implements Sim {

	@Override
	public void calling() {
		System.out.println("Airtel Calling");
	}

	@Override
	public void data() {
		System.out.println("Airtel Data");
	}

	@PostConstruct
	public void init() {
		System.out.println("Airtel bean is initialized");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Airtel bean will be destroyed");
	}
}

@Component
class Jio implements Sim {

	@Override
	public void calling() {
		System.out.println("Jio Calling");
	}

	@Override
	public void data() {
		System.out.println("Jio Data");
	}

	@PostConstruct
	public void init() {
		System.out.println("Jio bean is initialized");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Jio bean will be destroyed");
	}
}

@Component
class Bsnl implements Sim {

	@Override
	public void calling() {
		System.out.println("Bsnl Calling");
	}

	@Override
	public void data() {
		System.out.println("Bsnl Data");
	}

	@PostConstruct
	public void init() {
		System.out.println("Bsnl bean is initialized");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Bsnl bean will be destroyed");
	}
}

@Component
class Idea implements Sim {

	@Override
	public void calling() {
		System.out.println("Idea Calling");
	}

	@Override
	public void data() {
		System.out.println("Idea Data");
	}

	@PostConstruct
	public void init() {
		System.out.println("Idea bean is initialized");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Idea bean will be destroyed");
	}
}
