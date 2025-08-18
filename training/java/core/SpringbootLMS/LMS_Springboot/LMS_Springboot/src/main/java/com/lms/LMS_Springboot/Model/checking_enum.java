package com.lms.LMS_Springboot.Model;


import java.util.*;

public class checking_enum {

    public enum Status {
        ACTIVE("A"), INACTIVE("I");

        private final String type;

        Status(String type) {
            this.type = type;
        }

        private static final Map<String, Status> lookup = new HashMap<>();

        static {
            for (Status s : Status.values()) {
                lookup.put(s.getType(), s);
            }
        }

        public static Status getstatus(String type) {
            return lookup.get(type);
        }

        public String getType() {
            return this.type;
        }
    }

    public enum Availability {
        AVAILABLE("A"), ISSUED("I");

        private final String type;

        Availability(String type) {
            this.type = type;
        }

        private static final Map<String, Availability> lookup = new HashMap<>();

        static {
            for (Availability a : Availability.values()) {
                lookup.put(a.getType(), a);
            }
        }

        public static Availability getstatus(String type) {
            return lookup.get(type);
        }

        public String getType() {
            return this.type;
        }
    }

    public enum Gender {
        MALE("M"), FEMALE("F");

        private final String type;

        Gender(String type) {
            this.type = type;
        }

        private static final Map<String, Gender> lookup = new HashMap<>();

        static {
            for (Gender g : Gender.values()) {
                lookup.put(g.getType(), g);
            }
        }

        public static Gender getstatus(String type) {
            return lookup.get(type);
        }

        public String getType() {
            return this.type;
        }
    }

    public enum Status_issue {
        ISSUED("I"), RETURNED("R");

        private final String type;

        Status_issue(String type) {
            this.type = type;
        }

        private static final Map<String, Status_issue> lookup = new HashMap<>();

        static {
            for (Status_issue s : Status_issue.values()) {
                lookup.put(s.getType(), s);
            }
        }

        public static Status_issue getstatus(String type) {
            return lookup.get(type);
        }

        public String getType() {
            return this.type;
        }
    }
}
