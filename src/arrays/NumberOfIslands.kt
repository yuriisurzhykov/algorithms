package arrays

/**
 * A solution to the LeetCode question #200 (https://leetcode.com/problems/number-of-islands/description/)
 *
 * ## Description:
 *
 * Given an `m x n` 2D binary grid `grid` which represents a map of `'1'`s (land) and `'0'`s (water),
 * return the number of islands.
 *
 * An **island** is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * ### Example 1:
 * ```
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 * ```
 *
 * Example 2:
 * ```
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 * ```
 *
 * ### Constraints:
 *
 * - m == grid.length
 * - n == grid[i].length
 * - 1 <= m, n <= 300
 * - grid[i][j] is '0' or '1'.
 * */


enum class Direction(val adjustRow: Int, val adjustCol: Int) {
    RIGHT(0, 1),
    LEFT(0, -1),
    TOP(-1, 0),
    BOTTOM(1, 0);
}

class Point(val row: Int, val col: Int) {
    fun move(direction: Direction) = Point(
        row = row + direction.adjustRow,
        col = col + direction.adjustCol
    )
}

interface NumberOfIslands {

    fun numIslands(grid: Array<CharArray>): Int

    abstract class Abstract : NumberOfIslands {

        protected abstract fun islandExists(grid: Array<CharArray>, visited: Array<BooleanArray>, point: Point): Boolean

        final override fun numIslands(grid: Array<CharArray>): Int {
            checkGridSize(grid)

            val visitedCells = Array(grid.size) { index -> BooleanArray(grid[index].size) }
            var islandsCount = 0
            for (row in grid.indices) {
                for (col in grid[row].indices) {
                    val startPoint = Point(row, col)
                    if (islandExists(grid, visitedCells, startPoint)) islandsCount++
                }
            }
            println(visitedCells.joinToString("\n") { it.joinToString() })
            return islandsCount
        }

        protected fun checkGridSize(grid: Array<CharArray>) {
            check(grid.isNotEmpty()) {
                "The grid must contain at least on row"
            }
            check(grid[0].isNotEmpty()) {
                "The grid must contain at least one column."
            }
        }

        protected fun isValidPoint(
            grid: Array<CharArray>,
            visited: Array<BooleanArray>,
            point: Point
        ): Boolean {
            val col = point.col
            val row = point.row

            return row >= 0 && row < grid.size &&
                    col >= 0 && col < grid[row].size &&
                    grid[row][col] == ISLAND_CODE &&
                    !visited[row][col]

        }

        private companion object {
            const val ISLAND_CODE = '1'
        }
    }

    class BfsSolution : Abstract() {

        override fun islandExists(
            grid: Array<CharArray>,
            visited: Array<BooleanArray>,
            point: Point
        ): Boolean {
            if (!isValidPoint(grid, visited, point)) {
                return false
            }

            val queue = ArrayDeque<Point>()

            visited[point.row][point.col] = true

            queue.addLast(point)

            while (queue.isNotEmpty()) {
                val cell = queue.removeFirst()
                Direction.entries.forEach { direction ->
                    val neighbor = cell.move(direction)
                    if (isValidPoint(grid, visited, neighbor)) {
                        visited[neighbor.row][neighbor.col] = true
                        queue.addLast(neighbor)
                    }
                }
            }
            return true
        }
    }

    class DfsSolution : Abstract() {
        override fun islandExists(
            grid: Array<CharArray>,
            visited: Array<BooleanArray>,
            point: Point
        ): Boolean {
            if (!isValidPoint(grid, visited, point)) return false

            val stack = ArrayDeque<Point>()
            stack.addLast(point)
            visited[point.row][point.col] = true

            loop@ while (stack.isNotEmpty()) {
                val cell = stack.last()
                Direction.entries.forEach { direction ->
                    val neighbor = cell.move(direction)
                    if (isValidPoint(grid, visited, neighbor)) {
                        visited[neighbor.row][neighbor.col] = true
                        stack.addLast(neighbor)
                        continue@loop
                    }
                }
                stack.removeLast()
            }
            return true
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution: NumberOfIslands = DfsSolution()
            val example1 = arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
                charArrayOf('1', '1', '0', '1', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '0', '0', '0')
            )
            val example2 = arrayOf(
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('1', '1', '0', '0', '0'),
                charArrayOf('0', '0', '1', '0', '0'),
                charArrayOf('0', '0', '0', '1', '1')
            )
            val example3 = arrayOf(
                charArrayOf('1', '1', '1', '1', '0'),
            )
            println(solution.numIslands(example1))
            println(solution.numIslands(example2))
            println(solution.numIslands(example3))
        }
    }
}