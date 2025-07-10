package com.prana;

import java.util.Random;

record Order(long orderId, String item, int qty) { }

public class Main {
    private static final Random random = new Random();
    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        Shoewarehouse ware = new Shoewarehouse();
        Thread producerThread = new Thread(() -> {
            for (int j = 0; j < 10; j++) {
                Order order = new Order(
                    random.nextLong(1000000, 9999999),
                    Shoewarehouse.PRODUCT_LIST[random.nextInt(5)],
                    random.nextInt(1, 4)
                );
                ware.receiveOrder(order);
            }
        });
        producerThread.start();
        for (int i = 0; i < 2; i++) {
            Thread consumerThread = new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    Order item = ware.fulfillOrder();
                 }
            });
            consumerThread.start();
        }
    }
}