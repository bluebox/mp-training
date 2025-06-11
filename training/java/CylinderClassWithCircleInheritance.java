public class CylinderClassWithCircleInheritance{
    public static void main(String[] args){

        java.Rectangle rectangle=new java.Rectangle(5,10);
        System.out.println("rectcangle width="+rectangle.getWidth());
        System.out.println("rectangle length="+rectangle.getLength());
        System.out.println("rectangular area="+rectangle.getArea());

        java.Cuboid cuboid= new java.Cuboid(5,10,5);

        System.out.println("Cuboid width ="+cuboid.getWidth());
        System.out.println("cuboid length ="+cuboid.getLength());
        System.out.println("cuboid area="+cuboid.getArea());
        System.out.println("cuboid height="+cuboid.getHeight());
        System.out.println("cuboid volume="+cuboid.getVolume());
    }
}



