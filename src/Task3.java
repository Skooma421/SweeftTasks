import java.util.HashSet;

public class Task3 {
    public static int notContains(int[] array) {
        HashSet<Integer> positiveIntegers = new HashSet<>();

        for (int num : array) {
            if (num > 0) {
                positiveIntegers.add(num);
            }
        }

        int minMissing = 1;

        while (positiveIntegers.contains(minMissing)) {
            minMissing++;
        }

        return minMissing;
    }

    public static void main(String[] args) {
        int[] array = {3, 4, -1, 1};
        int result = notContains(array);
        System.out.println("The minimum positive integer not in the array is: " + result);
    }
}
