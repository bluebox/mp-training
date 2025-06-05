package HashMaps;

import java.util.HashMap;

public class Adventure {
	
	private Location start;
	
	public Adventure()
	{
		start = new Location("pandora", "Green hills and waterfalls");
		Location e1 = new Location("White Lands","Snowy plains, freezing temperature");
		Location w1 = new Location("Sahara","Scotching heat evarlasting desert");
		Location n1 = new Location("The Great Planes","Never ending grass lands");
		Location s1 = new Location("Rugged South","Rocky mountains");
		Location ne = new Location("Ocean","Stormy oceans");
		Location nw = new Location("NA","NA");
		Location se = new Location("NA","NA");
		Location sw = new Location("White Lands","Snowy plains, freezing temperature");
		start.neighbors.put("east", e1);
		start.neighbors.put("west", w1);
		start.neighbors.put("north", n1);
		start.neighbors.put("south", s1);
		e1.neighbors.put("north", ne);
		e1.neighbors.put("east", null);
		e1.neighbors.put("west", start);
		e1.neighbors.put("south",se);
		
		
		w1.neighbors.put("north",nw);
		w1.neighbors.put("south",sw);
		w1.neighbors.put("east",start);
		w1.neighbors.put("west",null);
		
		n1.neighbors.put("north",null);
		n1.neighbors.put("south",start);
		n1.neighbors.put("east",ne);
		n1.neighbors.put("west",nw);
		
		s1.neighbors.put("north",start);
		s1.neighbors.put("south", null);
		s1.neighbors.put("east", se);
		s1.neighbors.put("west", sw);
		
		ne.neighbors.put("north", null);
		ne.neighbors.put("south", e1);
		ne.neighbors.put("west",n1);
		ne.neighbors.put("east", null);
		
		nw.neighbors.put("north", null);
		nw.neighbors.put("south", w1);
		nw.neighbors.put("west",null);
		nw.neighbors.put("east", n1);
		
		se.neighbors.put("north", e1);
		se.neighbors.put("south", null);
		se.neighbors.put("west",s1);
		se.neighbors.put("east", null);
		
		sw.neighbors.put("north", w1);
		sw.neighbors.put("south", null);
		sw.neighbors.put("west",null);
		sw.neighbors.put("east", s1);
		
		
	}
	
	
	class Location
	{
		private String name;
		private String description;
		HashMap<String, Location> neighbors;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public Location(String name, String description) {
			super();
			this.name = name;
			this.description = description;
		}
		
		
		
	}
	
	public Location goToDirection(Location loc,String direction)
	{
		if(loc.neighbors.containsKey(direction))
			return loc.neighbors.get(direction);
		return null;
	}
	
	public static void main(String[] args) {
		Adventure ad = new Adventure();
		
	}

}
