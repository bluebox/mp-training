package GenericsCaseStudyChallenge;

import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ParcelDeliveryManager<T> {
	private List<Parcel<T>> parcels = new ArrayList<>();

	public void addParcel(T id, String city, int timeInHours) {
		Parcel<T> newParcel = new Parcel<T>(id, city, timeInHours);
		parcels.add(newParcel);
	}

	public Map<String, Double> getAverageDeliveryTimePerCity() {
		Map<String, List<Integer>> cityToTimesMap = new HashMap<>();

		for (Parcel<T> parcel : parcels) {
			String city = parcel.getCity();
			List<Integer> newCityEntry = cityToTimesMap.getOrDefault(city, new ArrayList<>());
			newCityEntry.add(parcel.getTimeInHours());
			cityToTimesMap.putIfAbsent(city, newCityEntry);
		}

		Map<String, Double> resultCityToAverageTimeMap = new HashMap();
		for (String city : cityToTimesMap.keySet()) {
			List<Integer> times = cityToTimesMap.get(city);
			Double sumOfTimes = 0D;
			for (int i = 0; i < times.size(); i++) {
				sumOfTimes += times.get(i);
			}
			double average = times.size() > 0 ? sumOfTimes / times.size() : 0.0;

			resultCityToAverageTimeMap.put(city, average);
		}
		return resultCityToAverageTimeMap;
	}

	List<T> getTopKSlowestDeliveredParcels(int k) {
		return parcels.stream().sorted((x, y) -> y.getTimeInHours() - x.getTimeInHours()).limit(k)
				.map(p -> p.getParcelID()).collect(Collectors.toList());

	}
}
