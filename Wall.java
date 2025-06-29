public class Wall {
    private double width;
    private double height;
    public Wall(){

    }
    public Wall(double height,double width){
        if(height<0){
            this.height=0;
        }
        else{
            this.height=height;
        }
        if(width<0){
            this.width=0;
        }
        else{
            this.width=width;
        }


    }
    public double getHeight() {
        return height;
    }
    public double getWidth() {
        return width;
    }
    public void setHeight(double height) {
        this.height = height;
        if (this.height<0)this.height=0;
    }
    public void setWidth(double width) {
        this.width = width;
        if(this.width<0)this.width=0;
    }
    public double getArea(){
        return height*width;
    }
}

