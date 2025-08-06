package com.example.beans;

import org.springframework.stereotype.Component;

@Component
public class Author {
    private String name;

    public Author() {
        // Default constructor
    }

    public Author(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Author [name=" + name + "]";
    }
}