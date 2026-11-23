import java.util.*;

/*
 * Two Sum (LeetCode 1)
 * Debug Your Career: mock coding interview
 *
 * Given an array of integers nums and an integer target, return the indices
 * of the two numbers that add up to target. Exactly one valid answer exists,
 * and you may not use the same element twice.
 */
public class Solution {

    // Approach 1: brute force. O(n^2) time, O(1) space.
    public static int[] twoSumBruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == complement) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0]; // fallback; the problem guarantees a solution
    }

    // Approach 2: HashMap. O(n) average time, O(n) space.
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> seen = new HashMap<>(); // value -> index

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // Check BEFORE inserting so we never use the same element twice
            if (seen.containsKey(complement)) {
                return new int[]{seen.get(complement), i};
            }
            seen.put(nums[i], i);
        }
        return new int[0]; // fallback; the problem guarantees a solution
    }

    // ---------------- Tests ----------------
    public static void main(String[] args) {
        int[][] nums    = { {2, 7, 11, 15}, {3, 2, 4}, {3, 3}, {-3, 4, 3, 90}, {0, 0}, {1, 2} };
        int[]   targets = {  9,             6,         6,      0,               0,      3    };
        int[][] expect  = { {0, 1},         {1, 2},    {0, 1}, {0, 2},          {0, 1}, {0, 1} };

        for (int i = 0; i < nums.length; i++) {
            int[] fast = twoSum(nums[i], targets[i]);
            int[] slow = twoSumBruteForce(nums[i], targets[i]);
            boolean ok = Arrays.equals(fast, expect[i]) && Arrays.equals(slow, expect[i]);
            System.out.println("Test " + (i + 1) + " | " + Arrays.toString(nums[i]) + ", target " + targets[i]
                + " | Expected: " + Arrays.toString(expect[i])
                + " | HashMap: " + Arrays.toString(fast)
                + " | Brute force: " + Arrays.toString(slow)
                + " | " + (ok ? "PASS" : "FAIL"));
        }
    }
}
