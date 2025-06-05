package GenericsCaseStudyChallenge;

public class Main {
	public static void main(String[] args) {
		ParcelDeliveryManager<String> deliveryManager = new ParcelDeliveryManager<String>();

		deliveryManager.addParcel("PAR121", "Konark", 8);
		deliveryManager.addParcel("PAR122", "Kurukshetra", 9);
		deliveryManager.addParcel("PAR123", "Konark", 13);
		deliveryManager.addParcel("PAR124", "Kurukshetra", 2);

		System.out.println(deliveryManager.getAverageDeliveryTimePerCity());

		System.out.println(deliveryManager.getTopKSlowestDeliveredParcels(2));

	}

}
