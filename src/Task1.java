public class Task1 {
    public static int singleNumber(int[] nums) {
        int result = 0;

        for (int num : nums) {
            result ^= num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 1, 4};
        int result = singleNumber(nums);
        System.out.println("The number that does not repeat is: " + result);
    }
}