package arrays

/**
 * A solution to the LeetCode question #347.
 *
 * ## Description:
 *
 * Given an integer array `nums` and an integer `k`, return the `k` most frequent elements within the array.
 *
 * The test cases are generated such that the answer is always unique.
 *
 * You may return the output in any order.
 *
 * ### Example 1:
 * ```
 * Input: nums = [1,2,2,3,3,3], k = 2
 *
 * Output: [2,3]
 * ```
 *
 * ### Example 2:
 * ```
 * Input: nums = [7,7], k = 1
 *
 * Output: [7]
 * ```
 *
 * ### Constraints:
 *
 * - 1 <= nums.length <= 10^4.
 * - -1000 <= nums[i] <= 1000
 * - 1 <= k <= number of distinct elements in nums.
 *
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 * */
interface TopKFrequentElements {
    fun topKFrequent(nums: IntArray, k: Int): IntArray

    class Solution : TopKFrequentElements {
        override fun topKFrequent(nums: IntArray, k: Int): IntArray {
            val frequencyMap = mutableMapOf<Int, Int>()
            nums.forEach { num ->
                frequencyMap[num] = frequencyMap.getOrDefault(num, 0) + 1
            }

            val results = IntArray(k)
            val bucketList = Array<MutableList<Int>>(nums.size + 1) { mutableListOf() }
            frequencyMap.forEach { (k, v) ->
                bucketList[v].add(k)
            }
            var resultIndex = 0
            for (i in bucketList.lastIndex downTo 0) {
                if (bucketList[i].isEmpty()) continue
                val iterator = bucketList[i].iterator()
                while (iterator.hasNext()) {
                    if (resultIndex >= k) {
                        return results
                    }
                    results[resultIndex] = iterator.next()
                    resultIndex++
                }
            }
            return results
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution: TopKFrequentElements = Solution()
            val example1 = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 2, 3, 1, 2, 6, 2)
            println(solution.topKFrequent(example1, 3).joinToString())
        }
    }
}