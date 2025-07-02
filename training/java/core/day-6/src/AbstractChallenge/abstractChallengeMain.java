package AbstractChallenge;;

public class abstractChallengeMain {
 public static void main(String []args) {
	 Store myStore=new Store();
	 myStore.addProduct(new classA("laptop",1200.00,"high performance","electronics"));
	 myStore.addProduct(new classB("mixy",1500.00,"high capacity","mixture"));

	 myStore.addProduct(new classA("tv",1900.00,"high display","watching"));

	 myStore.displayProducts();
	 myStore.addToOrder(1, 1);
	 myStore.addToOrder(3,3);
	 myStore.addToOrder(2,3);
	 myStore.addToOrder(1,3);
	 myStore.printOrder();
	
	 
 }
}
