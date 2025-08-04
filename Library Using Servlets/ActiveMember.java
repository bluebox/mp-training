package com.library.dto;

public class ActiveMember {
    private final String name;
    private final int booksIssued;

    public ActiveMember(String name, int booksIssued) {
        this.name = name;
        this.booksIssued = booksIssued;
    }

    public String getName() { return name; }
    public int getBooksIssued() { return booksIssued; }
}
