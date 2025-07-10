package com.prana;

public class Thread1 {
    static class EvenThread extends Thread {
        public void run() {
            try {
                for (int i = 0, count = 0; count < 5; i += 2) {
                    if (Thread.interrupted()) break;
                    System.out.println("Even: " + i);
                    count++;
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("EvenThread interrupted.");
            }
        }
    }

    public static void main(String[] args) {
    	// TODO Auto-generated method stub
        Runnable oddRunnable = () -> {
            try {
                for (int i = 1, count = 0; count < 5; i += 2) {
                    if (Thread.currentThread().isInterrupted()) break;
                    System.out.println("Odd: " + i);
                    count++;
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                System.out.println("OddThread interrupted.");
            }
        };
        Thread evenThread = new EvenThread();
        Thread oddThread = new Thread(oddRunnable);
        evenThread.start();
        oddThread.start();
        try {
            Thread.sleep(2500); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        evenThread.interrupt();
        oddThread.interrupt();
    }
}