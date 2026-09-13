package arrays

/**
 * This is a solution to the LeetCode question https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 * */
interface NumbersSmallerThanCurrent {

    fun smallerNumbersThanCurrent(nums: IntArray): IntArray

    class Naive : NumbersSmallerThanCurrent {
        override fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
            val returns = IntArray(nums.size) { 0 }
            nums.forEachIndexed { index, num ->
                for (j in 0..<nums.size) {
                    if (nums[j] < num) returns[index]++
                }
            }
            return returns
        }
    }

    class Sorted : NumbersSmallerThanCurrent {
        override fun smallerNumbersThanCurrent(nums: IntArray): IntArray {
            // Sort items before
            val sorted = nums.sorted()
            val indicesDict = mutableMapOf<Int, Int>()
            sorted.forEachIndexed { index, num ->
                if (indicesDict.containsKey(num)) return@forEachIndexed
                indicesDict[num] = index
            }
            val returns = IntArray(nums.size) { 0 }
            nums.forEachIndexed { index, num ->
                returns[index] = indicesDict[num] ?: 0
            }
            return returns
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = Sorted()
            val nums = intArrayOf(8, 1, 2, 2, 3)
            println(solution.smallerNumbersThanCurrent(nums).joinToString())
        }
    }
}