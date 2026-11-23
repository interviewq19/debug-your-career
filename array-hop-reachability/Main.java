public class Main {

    /*
     * ============================================================
     * PROBLEM: ARRAY HOP REACHABILITY
     * Debug Your Career: mock coding interview
     * ============================================================
     *
     * You are given an integer array hops of length n.
     * Each element hops[i] represents the exact number of
     * positions to jump from index i.
     *
     * Starting from index 0, determine whether you can visit
     * every index in the array at least once.
     * Return true if every index was visited, otherwise false.
     *
     * RULES:
     * 1. Always start at index 0.
     * 2. The starting index is considered visited.
     * 3. A value of 0 means stop.
     * 4. Stop if the next index is outside the array.
     *
     * CLARIFICATIONS (from the interview):
     * - Negative numbers mean jumping backward.
     * - Landing on a 0 still counts as visiting that index.
     * - Jumps can form a cycle; you must handle it.
     *
     * EXAMPLES:
     * [1, 1, 0]     -> true   (0 -> 1 -> 2)
     * [2, 1, 0]     -> false  (0 -> 2, index 1 never visited)
     * [1, 4, 0, 0]  -> false  (0 -> 1 -> 5 outside, 2 and 3 never visited)
     * [2, 0, -1]    -> true   (0 -> 2 -> 1)
     * [1, -1, 0]    -> false  (0 -> 1 -> 0 -> 1 ... loops forever)
     * [1, 1, -2]    -> true   (all visited before the cycle)
     *
     * CONSTRAINTS:
     * 1 <= hops.length <= 100000
     * -100000 <= hops[i] <= 100000
     * ============================================================
     */

    public static boolean canVisitAll(int[] hops) {
        // Only needed if the constraints weren't given
        if (hops == null || hops.length == 0) {
            return false;
        }

        int n = hops.length;
        boolean[] visited = new boolean[n]; // simpler than a HashSet: indices are 0..n-1
        int current = 0;
        int count = 0;

        // Keep going while we're inside the array and haven't been here before.
        // The path is deterministic, so a revisit means an infinite cycle.
        while (current >= 0 && current < n && !visited[current]) {
            visited[current] = true;
            count++;

            if (hops[current] == 0) {   // zero: can't move any further
                break;
            }
            current += hops[current];   // jump forward or backward
        }

        return count == n;
    }

    // Follow-up: if all jumps are nonnegative, every hops[i] (i < n-1) must be 1.
    // O(n) time, O(1) space.
    public static boolean canVisitAllNonNegative(int[] hops) {
        for (int i = 0; i < hops.length - 1; i++) {
            if (hops[i] != 1) return false;
        }
        return hops.length > 0;
    }

    // ============================================================
    // TEST CASES
    // ============================================================
    public static void main(String[] args) {
        int[][] tests = {
            {1, 1, 0},          // Example 1
            {2, 1, 0},          // Example 2
            {1, 4, 0, 0},       // Example 3: jumps out to the right
            {2, 0, -1},         // Example 4: backward jump
            {1, -1, 0},         // Example 5: cycle
            {1, 1, -2},         // Example 6: all visited before the cycle
            {5},                // single element jumps out, still visited
            {-1, 0},            // jumps out to the left
            {0, 1},             // zero at the start
            {3, 0, -1, -1},     // backward chain: 0 -> 3 -> 2 -> 1
            {2, 5, -2}          // cycle that skips index 1
        };
        boolean[] expected = {
            true, false, false, true, false, true,
            true, false, false, true, false
        };

        int passed = 0;
        for (int i = 0; i < tests.length; i++) {
            boolean actual = canVisitAll(tests[i]);
            boolean ok = actual == expected[i];
            if (ok) passed++;
            System.out.println("Test " + (i + 1)
                + " | " + java.util.Arrays.toString(tests[i])
                + " | Expected: " + expected[i]
                + " | Actual: " + actual
                + " | " + (ok ? "PASS" : "FAIL"));
        }
        System.out.println(passed + " / " + tests.length + " passed");

        // Follow-up check
        System.out.println("Nonnegative follow-up: "
            + (canVisitAllNonNegative(new int[]{1, 1, 0}) && !canVisitAllNonNegative(new int[]{2, 1, 0}) ? "PASS" : "FAIL"));
    }
}
