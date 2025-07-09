import java.util.*;
public class StreamTerminateOperations {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		List<String> list=Arrays.asList("Tarun","jagadees","adeesh","pavan","santosh","charan");
		System.out.println("For Each");
		list.stream().forEach(n->System.out.println(n));
		
		long count=list.stream().count();
		System.out.println(count);
		
		Optional<String> firstName=list.stream().findFirst();
		System.out.println(firstName);
		boolean allStartsWithT=list.stream().allMatch(
				n->n.startsWith("t")
				);
		System.out.println(allStartsWithT);
		
		boolean anyStartWithS=list.stream().anyMatch(
				n->n.startsWith(n));
		
		System.out.println(anyStartWithS);
		
		String concatinated=list.stream().reduce("", (a1,a2)->a1+" "+a2);
		System.out.println(concatinated);
				
		
	}

}
