package JAVAtraining;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
public class StreamTask {
	public static void main(String[] args) {
		List<Prod> prods=new ArrayList<>();
		prods.add(new Prod(1,"Milk",2,50));
		prods.add(new Prod(2,"Bread",1,60));
		prods.add(new Prod(3,"Oats",400));
		prods.add(new Prod(4,"Maggie",5,200));
		prods.add(new Prod(5,"Eggs",30,150));
		prods.add(new Prod(6,"Milk",2,50));
		
		String str = prods.stream().map(Prod::toString).collect(Collectors.joining("\t"));
		System.out.println(str);
		
		long count=prods.stream().collect(Collectors.counting());
		System.out.println(count);
		
		Map<String, List<Prod>> grpById = prods.stream().collect(Collectors.groupingBy(Prod::getProductName));
		System.out.println(grpById);
		
		List<Prod> prodWithQty = prods.stream().filter(prod->prod.getQuantity()>0).collect(Collectors.toList());
		System.out.println(prodWithQty);
		
		List<String> products = prods.stream().map(Prod->Prod.getProductName()).collect(Collectors.toList());
		System.out.println(products);
		
//		List<Prod> distinctprod= prods.stream().distinct().collect(Collectors.toList());
//		distinctprod.forEach(System.out::print);
//		System.out.println();
		
		List<Prod> sortedprod= prods.stream().sorted(Comparator.comparing(prod->prod.getCost())).collect(Collectors.toList());
		System.out.println(sortedprod);
		
		Map<Integer, String> prodmap = prods.stream().collect(Collectors.toMap(Id-> Id.getProductID(), Product->Product.getProductName()));
		System.out.println(prodmap);
		
		List<Prod> limitedProds = prods.stream().limit(2).collect(Collectors.toList());
		System.out.println(limitedProds);
		
		long tcost=prods.stream().map(Prod::getCost).reduce(0, (a, b) -> a + b);
		System.out.println(tcost);
		
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("Enter product id (enter 0 to exit): ");
			int prodid= sc.nextInt();
			if(prodid==0) break;
			else {
				Optional<Prod> productById = prods.stream().filter(prod -> prod.getProductID() == prodid).findAny();
				productById.ifPresentOrElse(
					    p -> System.out.println("Product found: " + p),
					    () -> System.out.println("Product with ID " + prodid + " not found."));
			}
		}
		System.out.println("Thank you!");
		sc.close();
	}
}
