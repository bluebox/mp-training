package library.model.enums;

public enum BookCategory {
    FICTION("Fiction","A"),
    SCIENCE("Science","I"),
    HISTORY("History","A"),
    BIOGRAPHY("Biography","A"),
    TECHNOLOGY("Technology","A"),
    FANTASY("Fantasy","A"),
    MYSTERY("Mystery","A"),
    THRILLER("Thriller","I"),
    ROMANCE("Romance","A"),
    OTHER("Other","I");

    private final String displayName;
    private final String code;

    BookCategory(String displayName,String code) {
        this.displayName = displayName;
        this.code=code;
    }

    public String getDisplayName() {
        return displayName;
    }
    
    public String getCode() {
        return code;
    }
    

    public static BookCategory fromDisplayName(String displayName) {
        for (BookCategory category : BookCategory.values()) {
            if (category.displayName.equalsIgnoreCase(displayName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No category with display name: " + displayName);
    }
}