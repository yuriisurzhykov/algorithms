package strings

/**
 * ## Problem Description
 * You are given an array of strings and need to group strings that belong to the same "shifting sequence".
 *
 * A shifting sequence is formed by repeatedly shifting letters in a string:
 *
 * - **Right shift**: Replace each letter with the next letter in the alphabet (a→b, b→c, ..., z→a)
 * - **Left shift**: Replace each letter with the previous letter in the alphabet (z←a, b←a, ..., a←z)
 *
 * For example, the strings "abc", "bcd", "xyz", "yza", and "zab" all belong to the same shifting sequence because:
 *
 * - "abc" can be right-shifted to "bcd"
 * - "bcd" can be right-shifted multiple times to eventually get "xyz"
 * - "xyz" can be right-shifted to "yza"
 * - "yza" can be right-shifted to "zab"
 * - "zab" can be right-shifted to "abc" (completing the cycle)
 *
 * The key insight is that two strings belong to the same shifting sequence if and only if they have the same pattern of differences between consecutive characters. For instance, "abc" and "xyz" both have the pattern where each character is exactly 1 position after the previous character in the alphabet.
 *
 * The solution works by normalizing each string to start with the letter 'a' while preserving the relative differences between characters. All strings that normalize to the same pattern belong to the same group. The algorithm:
 *
 * For each string, calculate how many positions to shift so the first character becomes 'a'
 * Apply this same shift to all characters in the string (wrapping around if needed)
 * Use the resulting normalized string as a key to group the original strings
 * Return the groups in any order.
 *
 * See the [link](https://algo.monster/liteproblems/249)
 * */
interface GroupShiftedStrings {

    fun groupShiftedString(strs: Array<String>): List<List<String>>

    class ShiftBased : GroupShiftedStrings {
        override fun groupShiftedString(strs: Array<String>): List<List<String>> {
            val alphabetSize = 26
            val result = HashMap<String, ArrayList<String>>()
            strs.forEachIndexed { i, str ->
                val shifts = mutableListOf<Int>()
                for (i in 0..<str.lastIndex) {
                    val currentCh = str[i]
                    val nextCh = str[i + 1]
                    shifts.add(((nextCh - currentCh) + alphabetSize) % alphabetSize)
                }
                result.getOrPut(shifts.joinToString("")) { ArrayList() }.add(str)
            }
            return result.values.toList()
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strs = arrayOf("abc", "bcd", "acef", "xyz", "az", "ba", "a", "z")
            val expected = listOf(
                listOf("abc", "bcd", "xyz"),
                listOf("acef"),
                listOf("az", "ba"),
                listOf("a", "z")
            )
            val solution: GroupShiftedStrings = ShiftBased()
            val message = solution.groupShiftedString(strs)
            println(message)
            assert(message == expected)
        }
    }
}