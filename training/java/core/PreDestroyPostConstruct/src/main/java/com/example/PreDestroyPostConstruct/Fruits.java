package com.example.PreDestroyPostConstruct;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
@Component
public class Fruits {
 private List<String>frt;
 @PostConstruct
  public void addFruits()
  {
	  System.out.println("Adding....");
	  frt=new ArrayList<>();
	  frt.add("apple");
	  frt.add("banana");
	  frt.add("mango");
	  System.out.println("added  fruits");
  }
  public List<String>getall()
  {
	  return frt;
  }
  @PreDestroy
  public void claerlst()
  {
	  frt.clear();
	  System.out.println("cleared list");
  }
}
