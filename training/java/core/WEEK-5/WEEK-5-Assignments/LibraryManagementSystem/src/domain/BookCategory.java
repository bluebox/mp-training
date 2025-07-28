package domain;

public enum BookCategory {
	
//	Defining all the category constants
	FICTION,NON_FICTION,MYSTERY,ROMANCE,SCIENCE_FICTION,FANTASY,HORROR;

//	Defining all the related functions
	public String getStringValue() {
		int ordinal=this.ordinal();
		switch(ordinal) {
		case 0:
			return "Fiction";
		case 1:
			return "Non Fiction";
		case 2:
			return "Mystery";
		case 3:
			return "Romance";
		case 4:
			return "Science Fiction";
		case 5:
			return "Fantasy";
		case 6:
			return "Horror";
		default:
			return "NA";
		}
	}
}
