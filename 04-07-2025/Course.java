package com.prana;

import java.util.Objects;

public class Course {
    private final String title;
    private final int lectureCount; 

    public Course(String title, int lectureCount) {
        this.title = Objects.requireNonNull(title);
        this.lectureCount = lectureCount;
    }

    public Course(String title) {
        this(title, 0);
    }

    public String getTitle() {
        return title;
    }

    public int getLectureCount() {
        return lectureCount;
    }

    @Override
    public String toString() {
        return title + (lectureCount > 0 ? " (" + lectureCount + " lectures)" : "");
    }
}
