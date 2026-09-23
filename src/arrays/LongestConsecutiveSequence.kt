package arrays

import kotlin.math.max

/**
 * The solution to the LeetCode coding question (128):
 * - https://leetcode.com/problems/longest-consecutive-sequence/
 * */
interface LongestConsecutiveSequence {
    fun longestConsecutive(nums: IntArray): Int

    class Solution : LongestConsecutiveSequence {
        override fun longestConsecutive(nums: IntArray): Int {
            val uniqueNums = nums.toSet()
            var answer = 0
            for (index in nums.indices) {
                val predecessor = nums[index] - 1
                if (!uniqueNums.contains(predecessor)) {
                    var localLongest = 1
                    var successor = nums[index] + 1
                    while (uniqueNums.contains(successor)) {
                        successor++
                        localLongest++
                    }
                    answer = max(answer, localLongest)
                }
            }
            return answer
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example = intArrayOf(1, 2, 5, 3, 6, 12, 7, 10, 4, 100, 4, 1)
            val solution: LongestConsecutiveSequence = Solution()
            println(solution.longestConsecutive(example))
            println("-----------------")
            val example2 = intArrayOf(1)
            println(solution.longestConsecutive(example2))
            println("-----------------")
            val example3 = intArrayOf()
            println(solution.longestConsecutive(example3))
        }
    }
}