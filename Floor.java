package corejavaday_Three;

public class Floor {
    private double width;
    private double height;

    public Floor(){}
    public Floor(double width, double height){
        this.width=(width<0)?0:width;
        this.height=(height<0)?0:height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = (width<0)?0:width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = (height<0)?0:height;
    }
    public double getArea(){
        return width*height;
    }
}
