package com.prana;

import java.util.*;

public class Theatre {
    private final String name;
    private final int seatsPerRow;
    private final TreeSet<Seat> seats = new TreeSet<>();

    public Theatre(String name, int numRows, int seatsPerRow) {
        if (numRows > 26) throw new IllegalArgumentException("Max 26 rows supported (A-Z)");
        this.name = name;
        this.seatsPerRow = seatsPerRow;

        for (char row = 'A'; row < 'A' + numRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                seats.add(new Seat(row, seatNum));
            }
        }
    }

    public boolean reserveSeat(String seatCode) {
        Seat dummy = new Seat(seatCode);
        Seat seat = seats.ceiling(dummy);
        if (seat != null && seat.equals(dummy) && !seat.isReserved()) {
            seat.reserve();
            return true;
        }
        return false;
    }

    public void printSeatMap() {
        System.out.println(" Seat Map for " + name);
        char currentRow = ' ';
        for (Seat seat : seats) {
            if (seat.getRow() != currentRow) {
                if (currentRow != ' ') System.out.println(); 
                currentRow = seat.getRow();
                System.out.print(currentRow + ": ");
            }
            System.out.print(seat + " ");
        }
        System.out.println("\n");
    }

    public List<String> reserveContiguousSeats(int count, char startRow, char endRow, int startSeat, int endSeat) {
        for (char row = startRow; row <= endRow; row++) {
            for (int i = startSeat; i <= endSeat - count + 1; i++) {
                List<Seat> block = new ArrayList<>();
                for (int j = 0; j < count; j++) {
                    Seat s = new Seat(row, i + j);
                    Seat real = seats.ceiling(s);
                    if (real == null || !real.equals(s) || real.isReserved()) {
                        block.clear(); 
                        break;
                    }
                    block.add(real);
                }
                if (block.size() == count) {
                    block.forEach(Seat::reserve);
                    return block.stream().map(Seat::getCode).toList();
                }
            }
        }
        return List.of();
    }

    private static class Seat implements Comparable<Seat> {
        private final String code;
        private boolean reserved;

        public Seat(char row, int number) {
            this.code = "%c%03d".formatted(row, number);
        }

        public Seat(String code) {
            this.code = code;
        }

        public char getRow() {
            return code.charAt(0);
        }

        public String getCode() {
            return code;
        }

        public boolean isReserved() {
            return reserved;
        }

        public void reserve() {
            reserved = true;
        }

        @Override
        public int compareTo(Seat o) {
            return this.code.compareTo(o.code);
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof Seat other) && code.equals(other.code);
        }

        @Override
        public int hashCode() {
            return code.hashCode();
        }

        @Override
        public String toString() {
            return reserved ? "[" + code + "]" : code;
        }
    }
    
    public void showAvailableInRow(char row) {
        seats.stream()
             .filter(s -> s.getRow() == row && !s.isReserved())
             .forEach(s -> System.out.print(s.getCode() + " "));
        System.out.println();
    }
}
