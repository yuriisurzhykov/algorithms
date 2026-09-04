package arrays

/**
 * This is a solution to the LeetCode question https://leetcode.com/problems/all-missing-numbers/
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

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
            val solution = BruteForce()
            println(solution.allMissingNumbers(nums))
        }
    }
}