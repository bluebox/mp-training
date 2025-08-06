package com.springexamples.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Calculator {

   //@Autowired 
   private Multiplier multiplier;

   public int add(int a, int b) {
      return a + b;
   }

   public int multiply(int a, int b) {
      return multiplier.multiply(a, b);
   }
}




