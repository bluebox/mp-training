package Collections;

public class Main {
	public static void main(String[] args) {
	
    Day today = Day.WEDNESDAY;

    if (today == Day.WEDNESDAY) {
        System.out.println("It's holiday!");
    }

 
    switch (today) {
        case SATURDAY:
        case SUNDAY:
            System.out.println("It's the weekend!");
            break;
        case MONDAY:
            System.out.println("It's Monday, start of the week.");
            break;
        default:
            System.out.println("It's a weekday.");
    }


    System.out.println("\nAll days of the week:");
    for (Day day : Day.values()) {
        System.out.println(day);
    }

	}

}