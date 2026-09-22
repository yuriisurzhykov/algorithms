package arrays

import kotlin.math.max

interface MaxWindowSum {

    fun maxWindowSum(nums: IntArray, k: Int): Int

    class Solution : MaxWindowSum {
        override fun maxWindowSum(nums: IntArray, k: Int): Int {
            var sum = 0
            for (i in 0..<k) {
                sum += nums[i]
            }
            var best = sum
            for (i in 1..<nums.size - k + 1) {
                sum = sum + nums[k + i - 1] - nums[i - 1]
                best = max(best, sum)
            }
            return best
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example1 = intArrayOf(2, 1, 5, 1, 3, 2)
            val window1 = 3

            val solution: MaxWindowSum = Solution()
            println(solution.maxWindowSum(example1, window1))

            val example2 = intArrayOf(1, 2, 3, 4, 5)
            val window2 = 2
            println(solution.maxWindowSum(example2, window2))

            val example3 = intArrayOf(5, 1, 2)
            val window3 = 3
            println(solution.maxWindowSum(example3, window3))
        }
    }
}