package com.lms.model;


public enum BookCategory {
    FICTION,
    NON_FICTION,
    MYSTERY,
    THRILLER,
    HORROR,
    ROMANCE,
    SCIENCE_FICTION,
    FANTASY,
    BIOGRAPHY,
    HISTORY,
    CHILDREN,
    EDUCATIONAL,
    POETRY,
    COMICS,
    SELF_HELP;

    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
