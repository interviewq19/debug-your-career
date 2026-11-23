# Array Hop Reachability

**Problem:** you're given an integer array `hops` of length `n`. Each value `hops[i]` is the exact number of positions to jump from index `i`. Starting at index `0` (which counts as visited), return `true` if every index is visited at least once, otherwise `false`.

**Rules as given:**
1. Always start at index 0.
2. The starting index is considered visited.
3. A value of 0 means stop.
4. Stop if the next index is outside the array.

The problem deliberately doesn't say which direction the jumps go, or what happens on a cycle. That's what the clarifying questions are for.

## Clarifying questions
| Ask | Answer |
|-----|--------|
| Can the array contain negative numbers? Do they mean backward jumps? | Yes, negative numbers mean jumping backward. |
| If the value at the current index is zero, should I stop? | Yes, you can't move any further. |
| If the next jump goes outside the array bounds, should I stop? | Yes. |
| If the jumps form a cycle, should I detect it to prevent an infinite loop? | Yes, you should handle that case. |
| Are we returning a boolean rather than the traversal path? | Yes, just true or false. |
| Can I assume the array is non-empty? | Yes. |

## Examples
| # | hops | Output | Path |
|---|------|--------|------|
| 1 | `[1, 1, 0]` | true | 0 → 1 → 2 |
| 2 | `[2, 1, 0]` | false | 0 → 2, index 1 never visited |
| 3 | `[1, 4, 0, 0]` | false | 0 → 1 → 5 (outside), indices 2 and 3 never visited |
| 4 | `[2, 0, -1]` | true | 0 → 2 → 1 (backward jump) |
| 5 | `[1, -1, 0]` | false | 0 → 1 → 0 → 1 … loops forever, index 2 never visited |
| 6 | `[1, 1, -2]` | true | 0 → 1 → 2, every index visited before the cycle |

## Approach
Each index has exactly one jump, so **the path is deterministic**: once you land on an index you've already visited, you'll repeat the same cycle forever. So simulate the jumps and stop as soon as you:
- jump outside the array,
- land on an already-visited index (a cycle), or
- land on a 0 (it still counts as visited).

Track visited indices with a `boolean[] visited` and keep a `count` of unique indices. At the end, return `count == n`.

**Why a boolean array instead of a HashSet?** Valid indices are always `0..n-1`, so a boolean array gives direct O(1) lookup with no hashing overhead, and it uses less memory.

## Complexity
- **Time O(n):** each index is visited at most once.
- **Space O(n):** for the visited array.

## Dry run: `[2, 0, -1]`
| current | hops[current] | count |
|---------|---------------|-------|
| 0 | 2 | 1 |
| 2 | -1 | 2 |
| 1 | 0 → break | 3 |

`count == n` → `3 == 3` → **true**

## Edge cases
- Single element `[5]`: jumps out of bounds, but index 0 was already visited → true
- Jump out to the left: `[-1, 0]` → false
- Zero at the start: `[0, 1]` → false
- Cycle before visiting everything: `[1, -1, 0]` → false
- Every index visited before a cycle: `[1, 1, -2]` → true
- If the constraints weren't given: return false for `null` or an empty array

## Follow-up: what if all jumps are nonnegative?
You can never move backward, so the only way to visit every index is `0 → 1 → 2 → … → n-1`. That means `hops[i] == 1` for every `i < n - 1`. No visited array is needed: O(n) time, **O(1) space**.

## Similar LeetCode problems
- Jump Game (55)
- Jump Game III (1306)
- Array Nesting (565)

## Run it
Paste `Main.java` into any online Java editor (for example the LeetCode Playground) and run it. All tests should print `PASS`.
