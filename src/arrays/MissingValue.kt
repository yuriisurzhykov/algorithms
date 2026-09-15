package arrays

/**
 *  The solution to LeetCode question: https://leetcode.com/problems/missing-number/
 * */
interface MissingValue {
    fun missingNumber(nums: IntArray): Int

    class BruteForce : MissingValue {
        override fun missingNumber(nums: IntArray): Int {
            // In Kotlin, sorted() gives O(n log n) time complexity, and O(N) space complexity.
            val sorted = nums.sorted()

            sorted.forEachIndexed { index, num ->
                // If the current number is not equal to its index, it means that
                // the missing number is the number that comes before the current [num].
                if (num != index) return num - 1
                // The edge case, where the length of the array is equal to the missing number.
                // In this case, the missing number is the `num + 1`
                if (index == sorted.size - 1) return num + 1
            }
            return -1
        }
    }

    class Fast : MissingValue {
        override fun missingNumber(nums: IntArray): Int {
            return nums.size * (nums.size + 1) / 2 - nums.sum()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(2147483645, 2147483647)
            println(nums.sum())
            val solution = Fast()
            println(solution.missingNumber(nums))
        }
    }
}