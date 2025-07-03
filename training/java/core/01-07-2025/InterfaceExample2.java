interface Example1
{
	public int getVolumeOfCuboid(int l,int b,int h);
}

interface Example2
{
	public int getVolumeOfCube(int a);
}

class C implements Example1,Example2
{
	public int getVolumeOfCuboid(int l,int b,int h)
	{
		return l*b*h;
	}	
	
	public int getVolumeOfCube(int a)
	{
		return a*a*a;
	}
}

public class InterfaceExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		C c=new C();
		System.out.println("Voume of the cuboid is "+c.getVolumeOfCuboid(3,4,6));
		System.out.println("Voluem of the Cube is "+c.getVolumeOfCube(5));
		

	}

}
