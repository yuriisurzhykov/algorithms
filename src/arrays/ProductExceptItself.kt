package arrays

/**
 * A solution to the LeetCode question [#238](https://leetcode.com/problems/product-of-array-except-self/)
 *
 * ## Description:
 *
 * Given an integer array `nums`, return an array `output` where `output[i]` is the product of all the
 * elements of nums except `nums[i]`.
 *
 * Each product is **guaranteed** to fit in a **32-bit** integer.
 *
 * Follow-up: Could you solve it in O(n) time without using the division operation?
 *
 * ### Example 1:
 * ```
 * Input: nums = [1,2,4,6]
 *
 * Output: [48,24,12,8]
 * ```
 *
 * ### Example 2:
 *
 * ```
 * Input: nums = [-1,0,1,2,3]
 *
 * Output: [0,-6,0,0,0]
 * ```
 *
 * ### Constraints:
 *
 * - 2 <= nums.length <= 100,000
 * - -30 <= nums[i] <= 30
 * - The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * */
interface ProductExceptItself {

    fun productExceptSelf(nums: IntArray): IntArray

    class WithDivision : ProductExceptItself {
        override fun productExceptSelf(nums: IntArray): IntArray {
            val result = IntArray(nums.size)
            var totalProduct = 0
            var zeroCount = 0
            for (i in nums.indices) {
                if (nums[i] != 0) {
                    totalProduct = if (totalProduct == 0) nums[i] else totalProduct * nums[i]
                } else if (zeroCount == 0) {
                    zeroCount++
                } else {
                    totalProduct = 0
                    break
                }
            }
            nums.forEachIndexed { index, num ->
                result[index] = when {
                    num == 0      -> totalProduct
                    zeroCount > 0 -> 0
                    else          -> totalProduct / num
                }
            }
            return result
        }
    }

    class WithoutDivision : ProductExceptItself {
        override fun productExceptSelf(nums: IntArray): IntArray {
            val result = IntArray(nums.size)
            val prefixProduct = IntArray(nums.size)
            val suffixProduct = IntArray(nums.size)

            var prefixTotalProduct = 1
            nums.forEachIndexed { index, num ->
                prefixTotalProduct *= num
                prefixProduct[index] = prefixTotalProduct
            }

            var suffixTotalProduct = 1
            for (i in nums.lastIndex downTo 0) {
                suffixTotalProduct *= nums[i]
                suffixProduct[i] = suffixTotalProduct
            }

            for (index in nums.indices) {
                when (index) {
                    0              -> result[index] = suffixProduct[1]
                    nums.lastIndex -> result[index] = prefixProduct[index - 1]
                    else           -> result[index] = prefixProduct[index - 1] * suffixProduct[index + 1]
                }
            }

            return result
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution: ProductExceptItself = WithoutDivision()
            val example1 = intArrayOf(1, 2, 3)
            val example2 = intArrayOf(1, 4, 4, 2, 0, -1)
            val example3 = intArrayOf(1, 2, 3, 4, 2, -2)

            println(solution.productExceptSelf(example1).joinToString())
            println(solution.productExceptSelf(example2).joinToString())
            println(solution.productExceptSelf(example3).joinToString())
        }
    }
}