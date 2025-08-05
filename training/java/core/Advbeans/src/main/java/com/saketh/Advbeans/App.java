package com.saketh.Advbeans;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	AnnotationConfigApplicationContext ctx=new AnnotationConfigApplicationContext(ProjectConfig.class);
    	Car car=ctx.getBean(Car.class);
    	Music mu=new Music();
    	mu.setName("Sony");
    	car.setMusic(mu);
    	Tyre tyre=new Tyre();
    	tyre.setName("MRF");
    	car.setTyre(tyre);
    	car.getTyre().rotate();
    	car.getMusic().play();
    	
    	//qualifier
    	OrderPizza ord=ctx.getBean(OrderPizza.class);
    	ord.order();
    	
    	//Singleton
    	SingletonClass obj1=ctx.getBean(SingletonClass.class);
    	SingletonClass obj2=ctx.getBean(SingletonClass.class);

    	System.out.println(obj1.hashCode());
    	System.out.println(obj2.hashCode());
    	System.out.println(obj1==obj2);
    	
    	//lazy
    	System.out.println("Before the lazy initialization");
    	LazyClass lc=ctx.getBean(LazyClass.class);
    	//Eager Initializer
    	System.out.println("Befor the Eager initializer");
    	Eagerclass ec=ctx.getBean(Eagerclass.class);
    	//Prototypescope
    	PrototypeScope ps1=ctx.getBean(PrototypeScope.class);
    	System.out.println(ps1.hashCode());
    	PrototypeScope ps2=ctx.getBean(PrototypeScope.class);
    	System.out.println(ps2.hashCode());

    }
}
