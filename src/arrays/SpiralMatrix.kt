package arrays

/**
 * ## Description:
 * Given an `m x n` matrix, return all elements of the matrix in spiral order.
 *
 * Example:
 * ```
 * Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * Output: [1,2,3,6,9,8,7,4,5]
 * ```
 *
 * Example 2:
 * ```
 * Input: matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 * ```
 * ## Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 * A solution to the LeetCode question (#54): https://leetcode.com/problems/spiral-matrix/
 * */
interface SpiralMatrix {

    fun spiralOrder(matrix: Array<IntArray>): List<Int>

    class Solution : SpiralMatrix {
        override fun spiralOrder(matrix: Array<IntArray>): List<Int> {
            val results = mutableListOf<Int>()
            var top = 0
            var left = 0
            var bottom = matrix.size - 1
            var right = matrix[0].size - 1
            while (left <= right && top <= bottom) {
                for (col in left..right) {
                    results.add(matrix[top][col])
                }
                top++

                for (row in top..bottom) {
                    results.add(matrix[row][right])
                }
                right--

                if (top <= bottom) {
                    for (col in right downTo left) {
                        results.add(matrix[bottom][col])
                    }
                    bottom--
                }

                if (left <= right) {
                    for (row in bottom downTo top) {
                        results.add(matrix[row][left])
                    }
                }
                left++
            }
            return results
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example1 = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(4, 5, 6),
                intArrayOf(7, 8, 9)
            )
            val example2 = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(3, 4),
                intArrayOf(5, 6),
                intArrayOf(7, 8),
                intArrayOf(9, 10)
            )
            val example3 = arrayOf(
                intArrayOf(1, 2, 3),
            )
            val solution: SpiralMatrix = Solution()
            println(solution.spiralOrder(example1))
            println(solution.spiralOrder(example2))
            println(solution.spiralOrder(example3))
        }
    }
}