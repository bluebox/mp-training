package samplecodes;

public class FlourPack {

	public static void main(String[] args) {
		System.out.println(isPackable(1, 0, 4));
	}

	public static boolean isPackable(int largeBags, int smallBags, int targetWeight) {
		int totalAvailableKilos = largeBags * 5 + smallBags;

		if (largeBags < 0 || smallBags < 0 || targetWeight < 0 || totalAvailableKilos < targetWeight) {
			return false;
		}

		if (totalAvailableKilos == targetWeight) {
			return true;
		}

		if (smallBags == 0) {
			return (largeBags * 5) % targetWeight == 0;
		}

		if (largeBags * 5 > targetWeight && (targetWeight % 5) <= smallBags) {
			return true;
		}

		int maxLargeBagsUsable = targetWeight / 5;
		int usedLargeBags = Math.min(largeBags, maxLargeBagsUsable);
		int remainingWeight = targetWeight - (usedLargeBags * 5);

		return remainingWeight <= smallBags;
	}
}
