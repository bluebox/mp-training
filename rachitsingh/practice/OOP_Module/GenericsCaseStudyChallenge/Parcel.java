package GenericsCaseStudyChallenge;

public class Parcel<T> {
	private T parcelID;
	private String city;
	private int timeInHours;

	public Parcel(T parcelID, String city, int timeInHours) {
		this.parcelID = parcelID;
		this.city = city;
		this.timeInHours = timeInHours;
	}

	public T getParcelID() {
		return parcelID;
	}

	public String getCity() {
		return city;
	}

	public int getTimeInHours() {
		return timeInHours;
	}

	@Override
	public String toString() {
		return parcelID + " | " + city + " | " + timeInHours;
	}
}
