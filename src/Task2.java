public class Task2 {
    public static int minSplit(int amount) {
        int[] denominations = {50, 20, 10, 5, 1};
        int numberOfCoins = 0;

        for (int denomination : denominations) {
            if (amount >= denomination) {
                int count = amount / denomination;
                numberOfCoins += count;
                amount -= count * denomination;
            }
        }

        return numberOfCoins;
    }

    public static void main(String[] args) {
        int amount = 78;
        int minCoins = minSplit(amount);
        System.out.println("Minimum number of coins needed: " + minCoins);
    }
}