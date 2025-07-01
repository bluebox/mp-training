public class Cuboid extends Rectangle {
    private double heigth;

    public Cuboid (double width,double length,double height){
        super(width, length);
        if(heigth<0){
             this.heigth = 0;

        }else{
             this.heigth=heigth;

        }

    } public double  getHeight (){
        return heigth;

    }public double getVolume(){
        return getArea() * heigth;

    }
}
