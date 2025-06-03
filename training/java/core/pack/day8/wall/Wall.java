package day8.wall;

public class Wall {
private double width,height;
 Wall(){
	 System.out.println("this is not arg constructor");
 }
 Wall(double width,double height){
	 this.width=width;
	 this.height=height;
	 if(width<0) {
		 this.width=0;
	 }
	 if(height<0) {
		 this.height=0;
	 }
	
 }
 public double getWidth() {
	 return this.width;
 }
 public double getHeight() {
	 return this.height;
 }
 public void setWidth(double width) {
	 if(width<0) {
		 this.width=0;
		 return;
	 }
	 this.width=width;
 }
 
 public void setHeight(double height) {
	 if(height<0) {
		 this.height=0;
		 return;
	 }
	 this.height=height;
 }
 public double getArea() {
	 return this.height*this.width;
 }
}
