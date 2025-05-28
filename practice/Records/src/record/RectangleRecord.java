package record;
record RectangleRecord(double length, double width) {
//  canonical constructor 
//	public Rectangle(double length, double width) { 
//        if (length <= 0 || width <= 0) {
//            throw new java.lang.IllegalArgumentException(
//                String.format("Invalid dimensions: %f, %f", length, width));
//        }
//        this.length = length;
//        this.width = width;
//    }
// compact constructor
//This succinct form of constructor declaration is only available in a record class. 
	public RectangleRecord {
        if (length <= 0 || width <= 0) {
            throw new java.lang.IllegalArgumentException(
                String.format("Invalid dimensions: %f, %f", length, width));
        }
    }
	
}
