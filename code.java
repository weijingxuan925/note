public class Code {
    public static void main(String[] args) {
        int[] array = {31,41,59,26,-53,58,97,-93,-23,84};
    }
    // Time Complexity: O(n^2)
    // 1. We are iterating through the array and calculating the sum of each sub-array.
    // 2. We are storing the sum in a variable called max.
    // 3. We are comparing the sum with the max variable and updating the max variable if the sum is greater than max.
    // 4. We are returning the max variable.
    public int maxSubRangeSum1(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                max = 0;
            }
        }
        return max;
    }
}