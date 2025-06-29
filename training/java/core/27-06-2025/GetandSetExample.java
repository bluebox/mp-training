
public class GetandSetExample {
  
	 String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GetandSetExample exm=new GetandSetExample();
		exm.setName("tarun");
		System.out.println("The name of the user is "+ exm.getName());
		
		

	}

}
