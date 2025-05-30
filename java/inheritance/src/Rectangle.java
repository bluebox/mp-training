package inheritence;

public class Rectangle {
	
		public double length = 10.0d;
		public double width = 10.0d;
		public Rectangle() {
			if(length < 0) {
				this.length = 0.0d;
			}
			else {
			this.length = length;
		}
			if(width < 0) {
				this.width = 0.0d;
			}
			else {
			this.width = width;
		}
		}
		public double getLengthWithoutAnyParameters(){
			return length;
		}
		public double getWidthWithoutAnyParameters(){
			return width;
		}
		public double getAreaWithoutAnyParameters(){
			return getLengthWithoutAnyParameters()*getWidthWithoutAnyParameters();
			
		}
	}

