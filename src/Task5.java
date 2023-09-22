public class Task5 {
    public static int countVariants(int stairsCount) {
        if (stairsCount <= 0) {
            return 0;
        } else if (stairsCount == 1) {
            return 1;
        }

        int[] waysToReach = new int[stairsCount + 1];
        waysToReach[1] = 1;
        waysToReach[2] = 2;

        for (int i = 3; i <= stairsCount; i++) {

            waysToReach[i] = waysToReach[i - 1] + waysToReach[i - 2];
        }

        return waysToReach[stairsCount];
    }

    public static void main(String[] args) {
        int stairsCount = 4;
        int variants = countVariants(stairsCount);
        System.out.println("Number of variants to climb " + stairsCount + " stairs: " + variants);
    }
}

