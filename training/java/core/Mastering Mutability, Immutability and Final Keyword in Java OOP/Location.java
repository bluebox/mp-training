import java.util.HashMap;
import java.util.Map;

public final class Location {
    private final int locationID;
    private final String description;
    private final Map<String, Integer> exits;

    public Location(int locationID, String description, Map<String, Integer> exits) {
        this.locationID = locationID;
        this.description = description;
        if(exits != null)
        {
            this.exits = new HashMap<String, Integer>(exits);

        } else {
            this.exits = new HashMap<String, Integer>();
        }
        this.exits.put("Q", 0);
    }


    public int getLocationID() {
        return locationID;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        try {
            Map<String, Integer> exitsClone = new HashMap<String, Integer>(exits);

            return (Map<String, Integer>) exitsClone;
        } catch (Exception e) {
            return null;
        }
    }

}

/* 
        ake the Location class an Immutable Class.

The strategy for creating an Immutable Class is:

    Steps:

    1. Don't provide setters.

    2. Make all fields final and private

    3. Don't allow the class to be subclassed.

    4. If the instance fields include references to mutable objects, don't allow those objects to be changed:   

            -  Don't provide methods that modify the mutable objects.

            -  Don't share references to the mutable objects.

As an added Task, handle the case where exits is null when passed to the constructor.

NOTE: Not all classes documented as "immutable" follow these rules. However, the steps above are the basis of an Immutable Class.
        """;
        */