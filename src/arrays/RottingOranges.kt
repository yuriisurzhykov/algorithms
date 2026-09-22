package arrays

/**
 * A solution to the LeetCode question #994: https://leetcode.com/problems/rotting-oranges/
 *
 * # Description
 *
 * You are given an `m x n` grid where each cell can have one of three values:
 *
 * - 0 representing an empty cell,
 * - 1 representing a fresh orange, or
 * - 2 representing a rotten orange.
 *
 * Every minute, any fresh orange that is **4-directionally** adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible,
 * return `-1`
 *
 * ## Example 1:
 * ```
 * Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 * Output: 4
 * ```
 *
 * ## Example 2:
 * ```
 * Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 * Output: -1
 * ```
 *
 * **Explanation**: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting
 * only happens 4-directionally.
 *
 * ## Example 3:
 * ```
 * Input: grid = [[0,2]]
 * Output: 0
 * ```
 *
 * **Explanation**: Since there are already no fresh oranges at minute 0, the answer is just 0.
 * */
interface RottingOranges {

    fun orangesRotting(grid: Array<IntArray>): Int

    class Solution : RottingOranges {

        enum class Direction(val adjRow: Int, val adjCol: Int) {
            RIGHT(0, 1),
            LEFT(0, -1),
            TOP(-1, 0),
            BOTTOM(1, 0)
        }

        private data class Point(val row: Int, val col: Int) {
            fun move(dir: Direction) = Point(
                row = row + dir.adjRow,
                col = col + dir.adjCol
            )

            fun isInRange(grid: Array<IntArray>) = row in grid.indices && col in grid[row].indices

            fun isRotten(grid: Array<IntArray>) = grid[row][col] == ROTTEN

            fun isFresh(grid: Array<IntArray>) = grid[row][col] == FRESH
        }

        override fun orangesRotting(grid: Array<IntArray>): Int {
            // Main problem: Iterate over every cell and find the smallest path,
            // to make all oranges become rotten.

            // Algorithm:
            // 1. Take a cell and run a BFS to find all oranges which are neighbors to the current.
            // 2. If cell ISN'T rotten -> return -1, which indicates that the current cell cannot
            // produce rotten neighbors.
            // 3. If cell IS rotten -> find all paths to make all oranges rotten.
            // 4. What do we expected in this example?
            // [
            //  [2, 1, 0],
            //  [0, 0, 1],
            //  [0, 1, 2]
            // ]
            // Summary:
            // Find a rotten orange, run BFS and keep iterations counter to count the number of
            // iterations to make all oranges rotten.

            if (grid.isEmpty() || grid[0].isEmpty()) {
                return -1
            }

            var minutesElapsed = 0
            var freshOranges = 0
            val rottenQueue = ArrayDeque<Point>()

            for (row in grid.indices) {
                for (col in grid[row].indices) {
                    val point = Point(row, col)
                    if (point.isRotten(grid)) {
                        rottenQueue.addLast(point)
                    } else if (point.isFresh(grid)) {
                        freshOranges++
                    }
                }
            }

            while (rottenQueue.isNotEmpty() && freshOranges > 0) {
                println("---while iter $minutesElapsed---")
                val queueSizeSnapshot = rottenQueue.size
                repeat(queueSizeSnapshot) {
                    val popped = rottenQueue.removeFirst()
                    println("---- cell $popped ----")
                    for (dir in Direction.entries) {
                        val neighbor = popped.move(dir)
                        if (!neighbor.isInRange(grid) || !neighbor.isFresh(grid)) {
                            continue
                        } else {
                            grid[neighbor.row][neighbor.col] = ROTTEN
                            rottenQueue.addLast(neighbor)
                            freshOranges--
                        }
                    }
                    println("---- end cell ----")
                }
                minutesElapsed++
            }

            return if (freshOranges == 0) minutesElapsed else -1
        }

        private companion object {
            const val ROTTEN = 2
            const val FRESH = 1
            const val EMPTY = 0
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example = arrayOf(
                intArrayOf(2, 1, 1),
                intArrayOf(1, 1, 0),
                intArrayOf(0, 1, 2)
            )
            println("---SAMPLE---")
            println(example.joinToString(separator = "\n") { it.joinToString("  ") })
            println("----END-----\n")
            val solution: RottingOranges = Solution()
            println(solution.orangesRotting(example))
        }
    }
}