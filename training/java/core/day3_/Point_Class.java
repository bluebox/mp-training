package com.day3_;

public class Point_Class {
    private int x;
    private int y;

    public Point_Class() {
        this.x = 0;
        this.y = 0;
    }
    public Point_Class(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public double distance() {
        return Math.sqrt(x * x + y * y);
    }
    public double distance(Point_Class another) {
        return Math.sqrt((another.x - this.x) * (another.x - this.x) +
                         (another.y - this.y) * (another.y - this.y));
    }
    public double distance(int x, int y) {
        return Math.sqrt((x - this.x) * (x - this.x) +
                         (y - this.y) * (y - this.y));
    }
}