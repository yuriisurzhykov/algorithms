package arrays

import kotlin.math.abs

/**
 * This is a solution to the LeetCode question https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * */
interface AllMissingNumbers {
    fun allMissingNumbers(nums: IntArray): List<Int>

    class BruteForce : AllMissingNumbers {
        override fun allMissingNumbers(nums: IntArray): List<Int> {
            val unique = nums.toSet()
            val returns = mutableSetOf<Int>()
            for (i in 1..nums.size) {
                if (i !in unique) returns.add(i)
            }
            return returns.toList()
        }
    }

    class MemoryEfficient : AllMissingNumbers {
        override fun allMissingNumbers(nums: IntArray): List<Int> {
            for (i in nums.indices) {
                val index = abs(nums[i]) - 1
                if (nums[index] > 0) nums[index] = -nums[index]
            }

            return nums.indices.mapNotNull {
                if (nums[it] > 0) it + 1 else null
            }
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 2, 3, 3, 5, 9, 7, 8, 9, 9)
            val solution = MemoryEfficient()
            println(solution.allMissingNumbers(nums))
        }
    }
}