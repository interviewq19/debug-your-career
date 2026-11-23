# Two Sum (LeetCode 1)

**Problem:** given an array of integers `nums` and an integer `target`, return the indices of the two numbers that add up to `target`. Exactly one valid answer exists, and you can't use the same element twice.

```
nums = [2, 7, 11, 15], target = 9  →  [0, 1]
```

Problem link: https://leetcode.com/problems/two-sum/

## Clarifying questions
- Is the array sorted? → No.
- Can it contain duplicates? → Yes.
- Is there always exactly one valid pair? → Yes.
- What if there's no solution? → Not possible, but return an empty array as a fallback.

## Approach 1: brute force
Check every pair with two nested loops. Start the inner loop at `i + 1` so you never reuse an element or check a pair twice.
- Time **O(n²)**, space **O(1)**

## Approach 2: HashMap (optimal)
Store each number you've seen with its index (`value → index`). For each number, compute `complement = target - nums[i]`. If the complement is already in the map, return both indices. Otherwise, store the current number.

**Check before inserting.** That way the same element is never matched with itself, and duplicates like `[3, 3]` still work.
- Time **O(n)** on average, space **O(n)**

## Dry run: `[3, 2, 4]`, target 6
| i | current | complement | map after | result |
|---|---------|------------|-----------|--------|
| 0 | 3 | 3 | {3=0} | continue |
| 1 | 2 | 4 | {3=0, 2=1} | continue |
| 2 | 4 | 2 | | found → [1, 2] |

## Edge cases
| Case | Input | Expected |
|------|-------|----------|
| Duplicates | `[3, 3]`, 6 | `[0, 1]` |
| Negatives | `[-3, 4, 3, 90]`, 0 | `[0, 2]` |
| Zeros | `[0, 0]`, 0 | `[0, 1]` |
| Minimum size | `[1, 2]`, 3 | `[0, 1]` |

## Run it
Paste `Solution.java` into any online Java editor and run it. All tests should print `PASS`.
