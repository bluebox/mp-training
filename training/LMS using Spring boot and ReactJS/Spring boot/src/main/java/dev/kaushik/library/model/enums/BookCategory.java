package dev.kaushik.library.model.enums;

public enum BookCategory {
    FICTION("Fiction"),
    SCIENCE("Science"),
    HISTORY("History"),
    BIOGRAPHY("Biography"),
    TECHNOLOGY("Technology"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    THRILLER("Thriller"),
    ROMANCE("Romance"),
    OTHER("Other");

    private final String displayName;

    BookCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
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