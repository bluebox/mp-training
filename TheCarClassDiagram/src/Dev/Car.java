package Dev;

public class Car {
	
		private String description;
		
		public Car(String description) {
			this.description=description;
		}
		public String startEngine() {
			return "car engine started()";
		}
		public String drive() {
			return "driving started";
		}
		protected String runEngine() {
			return "car engine running";
		}
		public String getDescription() {
			return description;
		}
}

