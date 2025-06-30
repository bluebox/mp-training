public class FlourPacker {

    public static boolean canPack(int bigCount, int smallCount, int goal) {
        // 1. Check for invalid input (negative parameters)
        if (bigCount < 0 || smallCount < 0 || goal < 0) {
            return false;
        }

        // 2. Calculate the total kilos from big bags
        int bigKilos = bigCount * 5;

        // 3. Check if big bags alone can meet or exceed the goal
        // If bigKilos is greater than or equal to goal, we need to check if the remaining goal
        // (goal % 5) can be covered by small bags. If goal % 5 is 0, then big bags alone are sufficient.
        // If goal % 5 is not 0, we need to ensure enough small bags are available to cover the remainder.
        if (bigKilos >= goal) {
            return (goal % 5) <= smallCount;
        } else {
            // 4. If big bags are not enough, check if big bags + small bags can meet the goal
            // The remaining goal is goal - bigKilos. We need to check if smallCount can cover this remaining amount.
            return (bigKilos + smallCount) >= goal;
        }
    }
}