package arrays

/**
 * A solution to the LeetCode problem [#36](https://leetcode.com/problems/valid-sudoku/)
 *
 * # Description:
 *
 * Determine if a `9 x 9` Sudoku board is valid. Only the filled cells need to be validated according
 * to the **following rules**:
 *
 * - Each row must contain the digits 1-9 without repetition.
 * - Each column must contain the digits 1-9 without repetition.
 * - Each of the nine 3 x 3 sub-boxes of the grid must contain the digits 1-9 without repetition.
 *
 * **Note**:
 *
 * - A Sudoku board (partially filled) could be valid but is not necessarily solvable.
 * - Only the filled cells need to be validated according to the mentioned rules.
 *
 * ### Example 1:
 * ```
 * Input: board =
 * [["1","2",".",".","3",".",".",".","."],
 *  ["4",".",".","5",".",".",".",".","."],
 *  [".","9","8",".",".",".",".",".","3"],
 *  ["5",".",".",".","6",".",".",".","4"],
 *  [".",".",".","8",".","3",".",".","5"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".",".",".",".",".",".","2",".","."],
 *  [".",".",".","4","1","9",".",".","8"],
 *  [".",".",".",".","8",".",".","7","9"]]
 * Output: true
 * ```
 *
 * ### Example 2:
 * ```
 * Input: board =
 * [["1","2",".",".","3",".",".",".","."],
 *  ["4",".",".","5",".",".",".",".","."],
 *  [".","9","1",".",".",".",".",".","3"],
 *  ["5",".",".",".","6",".",".",".","4"],
 *  [".",".",".","8",".","3",".",".","5"],
 *  ["7",".",".",".","2",".",".",".","6"],
 *  [".",".",".",".",".",".","2",".","."],
 *  [".",".",".","4","1","9",".",".","8"],
 *  [".",".",".",".","8",".",".","7","9"]]
 *
 * Output: false
 * ```
 *
 * Explanation: There are two 1's in the top-left 3x3 sub-box.
 *
 * ### Constraints:
 *
 * - `board.length == 9`
 * - `board[i].length == 9`
 * - `board[i][j]` is a digit `1-9` or '.'.
 * */
interface ValidSudoku {

    fun isValidSudoku(board: Array<CharArray>): Boolean

    class Solution : ValidSudoku {
        override fun isValidSudoku(board: Array<CharArray>): Boolean {
            val rowsUnique = Array(board.size) { mutableSetOf<Char>() }
            val colsUnique = Array(board.size) { mutableSetOf<Char>() }
            val windowsUnique = Array(board.size) { mutableSetOf<Char>() }

            for (row in board.indices) {
                for (col in board[row].indices) {
                    val ch = board[row][col]
                    val windowIndex = windowIndex(row, col)
                    if (!ch.isDigit()) continue
                    if (!rowsUnique[row].contains(ch) &&
                        !colsUnique[col].contains(ch) &&
                        !windowsUnique[windowIndex].contains(ch)
                    ) {
                        rowsUnique[row].add(ch)
                        colsUnique[col].add(ch)
                        windowsUnique[windowIndex].add(ch)
                    } else {
                        return false
                    }
                }
            }

            return true
        }

        private fun windowIndex(row: Int, col: Int): Int {
            return (row / 3) * 3 + (col / 3)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example = arrayOf(
                charArrayOf('1', '2', '.', '.', '3', '.', '.', '.', '.'),
                charArrayOf('4', '.', '.', '5', '.', '.', '.', '.', '.'),
                charArrayOf('.', '9', '8', '.', '.', '.', '.', '.', '3'),
                charArrayOf('5', '.', '.', '.', '6', '.', '.', '.', '4'),
                charArrayOf('.', '.', '.', '8', '.', '3', '.', '.', '5'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '.', '.', '.', '.', '.', '2', '.', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '8'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )

            val falseExample = arrayOf(
                charArrayOf('1', '2', '.', '.', '3', '.', '.', '.', '.'),
                charArrayOf('4', '.', '.', '5', '.', '.', '.', '.', '.'),
                charArrayOf('.', '9', '1', '.', '.', '.', '.', '.', '3'),
                charArrayOf('5', '.', '.', '.', '6', '.', '.', '.', '4'),
                charArrayOf('.', '.', '.', '8', '.', '3', '.', '.', '5'),
                charArrayOf('7', '.', '.', '.', '2', '.', '.', '.', '6'),
                charArrayOf('.', '.', '.', '.', '.', '.', '2', '.', '.'),
                charArrayOf('.', '.', '.', '4', '1', '9', '.', '.', '8'),
                charArrayOf('.', '.', '.', '.', '8', '.', '.', '7', '9')
            )

            val solution: ValidSudoku = Solution()
            println(solution.isValidSudoku(example))
            println(solution.isValidSudoku(falseExample))
        }
    }
}