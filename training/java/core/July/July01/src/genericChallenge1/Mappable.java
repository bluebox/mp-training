package genericChallenge1;

public interface Mappable {
	
    void render();

    static double[] stringToLatLon(String location) {

        var splits = location.split(",");
        double lat = Double.valueOf(splits[0]);
        double lng = Double.valueOf(splits[1]);
        return new double[]{lat, lng};
    }
}

