package com.example.PreDestroyPostConstruct;

import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class frutControl {
 private final Fruits fs;

 public frutControl(Fruits fs) {
	super();
	this.fs = fs;
 }
 public List<String>getFruits()
 {
	 return fs.getall();
 }
}
