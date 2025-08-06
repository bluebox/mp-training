package com.example.MultiBeanHandling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
@Component
public class ServiceConsumer {
	

	    private final Service Service1;
	    private final Service Service2;

	    @Autowired
	    public ServiceConsumer(@Qualifier("Service1") Service Service1, @Qualifier("Service2") Service Service2) {
	        this.Service1 =Service1;
	        this.Service2 = Service2;
	    }

	   
	 public void useServices()
	 {
		 System.out.println(Service1.getName());
		 System.out.println(Service2.getName());
	 }

}
