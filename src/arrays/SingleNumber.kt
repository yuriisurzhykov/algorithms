package arrays

interface SingleNumber {
    fun singleNumber(nums: IntArray): Int

    class BruteForce : SingleNumber {
        override fun singleNumber(nums: IntArray): Int {
            val actualSize = nums.size / 2 + 1
            val expectedSum = actualSize * (actualSize + 1) / 2
            return 2 * expectedSum - nums.sum()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 2, 1, 3, 3, 2, 5, 5, 4)
            val solution = BruteForce()
            println(solution.singleNumber(nums))
        }
    }
}