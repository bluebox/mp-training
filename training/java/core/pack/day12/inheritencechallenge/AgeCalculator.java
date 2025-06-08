package day12.inheritencechallenge;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class AgeCalculator {

    public static String calculateAge(String birthDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy"); // Adjust if needed
        LocalDate birthDate = LocalDate.parse(birthDateString, formatter);
        LocalDate currentDate = LocalDate.now();

        if ((birthDate != null) && (currentDate != null)) {
            Period age = Period.between(birthDate, currentDate);
            return age.getYears() + " years and " + age.getMonths() + " months";
        } else {
            return "Invalid date";
        }
    }

}

