package gymManagement;

public record ProgressRecord(double weight, String date) {
    public String displayRecord() {
        return String.format("Date: %s, Weight: %.2f kg", date, weight);
    }
}