package com;

public class ForLoop {

    public static void main(String[] args) {
        // write your code here
        // loop from 1 till 9 (included)
        int i = 1;
        for (; i < 10; i++) {
            System.out.println(i);
        }

        for (int j = 1;; j++) {
            System.out.println(j);
            if (j == 10)
                break;
        }

        /// This works like normal for loop with inc
        for (int j = 10; j <= 20;) {
            System.out.println(j);
            j++;
        }

        // infinite loop -- only condition and no in/dec.
        // for(;10<20;) {
        // System.out.println("hi");
        // }

        // Infinite loop -- No condition
        // for(int k=10;;k++) {
        // System.out.println(k);
        // }

        // Infinite loop -- No condition and no increment/decrement
        // for(int j=10;;) {
        // System.out.println(j);
        // }

        // Infinite loop -- no loop variable and no initialisation, no condition and no
        // inc/dec
        // for(;;) {
        // System.out.println("Hi");
        // }
    }
}
