package arrays

/**
 * Solution to the LeetCode question https://leetcode.com/problems/single-number/ with two different problems.
 * */
interface SingleNumber {
    fun singleNumber(nums: IntArray): Int

    class Progression : SingleNumber {
        override fun singleNumber(nums: IntArray): Int {
            val actualSize = nums.size / 2 + 1
            val expectedSum = actualSize * (actualSize + 1) / 2
            return 2 * expectedSum - nums.sum()
        }
    }

    class LeetCode : SingleNumber {
        override fun singleNumber(nums: IntArray): Int {
            val unique = mutableSetOf<Int>()
            nums.forEach { num ->
                unique.add(num)
            }

            val expectedSum = unique.sum() * 2
            return expectedSum - nums.sum()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 2, 1, 3, 3, 2, 5, 5, 4)
            val solution = Progression()
            println(solution.singleNumber(nums))
        }
    }
}