package com.springexamples.beans;

import org.springframework.stereotype.Component;

@Component
class Multiplier {
  public int multiply(int a, int b) {
     return a * b;
  }
}