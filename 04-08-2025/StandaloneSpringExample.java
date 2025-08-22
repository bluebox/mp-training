package mymaven;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class StandaloneSpringExample {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService service = context.getBean(MyService.class);
        service.printMessage();
        Vehicle v = context.getBean(Vehicle.class);
        System.out.println("Vehicle of Ram is "+v.getName());
        Vehicle ve = context.getBean(Bike.class);
        System.out.println("Vehicle of Ram is "+ve.getName());
        ((AnnotationConfigApplicationContext) context).close();
    }
}
