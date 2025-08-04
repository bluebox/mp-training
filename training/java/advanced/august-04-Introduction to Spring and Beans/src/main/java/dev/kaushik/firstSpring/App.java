package dev.kaushik.firstSpring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

	public static void main(String[] args) {
		var context = new AnnotationConfigApplicationContext(SimConfig.class);
		
		Sim sim = context.getBean("jioSim", Sim.class);
		sim.calling();
		sim.data();
		
		Sim sim2 = (Sim) context.getBean("airtel");
		sim2.calling();
		sim2.data();

		Sim primarySim= context.getBean(Sim.class);
		primarySim.calling();
		primarySim.data();
		
		System.out.println("checking post construct and pre destroy");
		context.close();
		
	}
}
