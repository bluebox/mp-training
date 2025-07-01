package DistanceBtnPoints;

public class Point {
 private int x;
 private int y;
 public Point()
 {
	 this.x=0;
	 this.y=0;
 }
  public Point(int x,int y)
  {
	  this.x=x;
	  this.y=y;
	  
  }
  public int  getx()
  {
	  return this.x;
  }
  public int gety()
  {
	  return this.y;
  }
  public void setx(int x)
  {
	  this.x=x;
  }
  public void sety(int y)
  {
	  this.y=y;
  }
  public double distance()
  {
	  return Math.sqrt(Math.pow(this.x-0,2)+Math.pow(this.y-0,2));
  }
  public double distance(Point single)
  {
	  return Math.sqrt(Math.pow(this.x - single.getx(),2)+Math.pow(this.y-single.gety(),2));
  }
  public double distance(int x,int y)
  {
	  return Math.sqrt(Math.pow(this.x - x,2)+Math.pow(this.y-y,2));
  }
}
