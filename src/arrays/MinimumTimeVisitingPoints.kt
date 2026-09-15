package arrays

import kotlin.math.abs

/**
 * ## Description
 * On a 2D plane, there are n points with integer coordinates `points[i] = [xi, yi]`. Return the minimum time in
 * seconds to visit all the points in the order given by points.
 *
 * You can move according to these rules:
 *
 *  * In `1` second, you can either:
 *      - move vertically by one unit,
 *      - move horizontally by one unit, or
 *      - move diagonally `sqrt(2)` units (in other words, move one unit vertically then one unit horizontally
 *      in 1 second).
 *  * You have to visit the points in the same order as they appear in the array.
 *  * You are allowed to pass through points that appear later in the order, but these do not count as visits.
 *
 * Example 1:
 * ```kotlin
 * Input: points = [[1,1],[3,4],[-1,0]]
 * Output: 7
 * Explanation: One optimal path is [1,1] -> [2,2] -> [3,3] -> [3,4] -> [2,3] -> [1,2] -> [0,1] -> [-1,0]
 * Time from [1,1] to [3,4] = 3 seconds
 * Time from [3,4] to [-1,0] = 4 seconds
 * Total time = 7 seconds
 * ```
 *
 * Example 2:
 * ```
 * Input: points = [[3,2],[-2,2]]
 * Output: 5
 * ```
 *
 * The solution to the LeetCode question #1266. https://leetcode.com/problems/minimum-time-visiting-all-points/
 * */
interface MinimumTimeVisitingPoints {
    fun minTimeToVisitAllPoints(points: Array<IntArray>): Int

    class MathBased : MinimumTimeVisitingPoints {
        override fun minTimeToVisitAllPoints(points: Array<IntArray>): Int {
            if (points.isEmpty() || points.size == 1) return 0
            var totalSeconds = 0
            for (i in 1..points.lastIndex) {
                val distanceX = abs(points[i][0] - points[i - 1][0])
                val distanceY = abs(points[i][1] - points[i - 1][1])
                totalSeconds += maxOf(distanceY, distanceX)
            }
            return totalSeconds
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val examplePoints = arrayOf(
                intArrayOf(1, 1),
                intArrayOf(3, 4),
                intArrayOf(-1, 0)
            )
            val solution: MinimumTimeVisitingPoints = MathBased()
            println(solution.minTimeToVisitAllPoints(examplePoints))
            val examplePoints2 = arrayOf(
                intArrayOf(3, 2),
                intArrayOf(-2, 2),
            )
            println(solution.minTimeToVisitAllPoints(examplePoints2))
        }
    }
}