package arrays

/**
 * A solution to the LeetCode question https://leetcode.com/problems/two-sum/
 * */
interface TwoSum {

    fun twoSum(nums: IntArray, target: Int): IntArray

    class BruteForce : TwoSum {
        override fun twoSum(nums: IntArray, target: Int): IntArray {
            for (i in nums.indices) {
                for (j in i + 1..<nums.size) {
                    if (nums[i] + nums[j] == target) return intArrayOf(i, j)
                }
            }
            return intArrayOf()
        }
    }

    class HashTable : TwoSum {
        override fun twoSum(nums: IntArray, target: Int): IntArray {
            val existingIndices = mutableMapOf<Int, Int>()
            nums.forEachIndexed { index, num ->
                val complement = target - num
                if (existingIndices.containsKey(complement)) {
                    return intArrayOf(existingIndices[complement]!!, index)
                }
                existingIndices[num] = index
            }
            return intArrayOf()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution = HashTable()
            val nums = intArrayOf(2, 7, 11, 15)
            val target = 9
            println(solution.twoSum(nums, target).joinToString())
        }
    }
}