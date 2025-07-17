package challenge2jdbc;

import challenge2jdbc.OrderController;

public class OrderApp {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        service.processCSVFile("orders.csv"); 
        

        String orderTime = "2023-11-01 06:01:00";
        String jsonFilePath = "Orderitems.json";

        service.callAddOrderProcedureFromFile(orderTime, jsonFilePath);


        OrderController controller = new OrderController();
        controller.runApp(); 
    }
}
