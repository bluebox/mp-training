package java;

public record Song(String name, double duration) {
    public String getName() {
        return name;
    }

    public double getduration() {
        return duration;
    }

    public String toString() {
        return name + "::" + duration;
    }
}
