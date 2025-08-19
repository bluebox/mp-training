package com.lms.model;


public enum BookCategory {
    FICTION,
    NONFICTION,
    MYSTERY,
    THRILLER,
    HORROR,
    ROMANCE,
    SCIENCEFICTION,
    FANTASY,
    BIOGRAPHY,
    HISTORY,
    CHILDREN,
    EDUCATIONAL,
    POETRY,
    COMICS,
    SELFHELP;

    @Override
    public String toString() {
        String name = name().toLowerCase().replace('_', ' ');
        return Character.toUpperCase(name.charAt(0)) + name.substring(1);
    }
}
