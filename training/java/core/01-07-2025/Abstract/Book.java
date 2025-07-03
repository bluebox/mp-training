package Abstract;

 class Book extends Product{
	private String author;
	public Book(String name,double price,String author)
	{
		super(name,price);
		this.author=author;
	}
	public void displayDetails()
	{
		System.out.println("Name of the Book is: "+getName()+", Name of the Author is: "+ author +" ,Price of the Book is :" +getPrice()+" Rs \n");
	}

}
 
 class Gadget extends Product
 {
	 private String brand;
	 public Gadget(String name,double price,String brand)
	 {
		 super(name,price);
		 this.brand=brand;
		 
	 }
	 
	 public void displayDetails()
	 {
		 System.out.println("Name of the Book is: "+getName()+", Brand is : "+ brand +" ,Price of the Book is :" +getPrice()+" Rs \n");
	 }
 }
