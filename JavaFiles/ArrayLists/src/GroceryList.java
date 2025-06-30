import java.util.ArrayList;

public class GroceryList {
	
	public record grocery(String name,int count) {
		
		public grocery(String name,int count) {
			this.count = count;
			this.name=name;
		}

		@Override
		public String toString() {
			return "grocery [Item=" + name + ", Quantity=" + count + "]";
		}
		
		
		
	}

	public static void main(String[] args) {
		
		ArrayList<grocery> gs= new ArrayList<>();
		
		gs.add(new grocery("Milk",2));
		gs.add(new grocery("Butter",3));

		gs.add(new grocery("Bread",5));
		
		System.out.println(gs);

	}

}

