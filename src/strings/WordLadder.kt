package strings

/**
 * A solution to the LeetCode question [#127](https://leetcode.com/problems/word-ladder/description/)
 *
 * # Description:
 *
 * A **transformation sequence** from word `beginWord` to word `endWord` using a dictionary `wordList`
 * is a sequence of words `beginWord -> s1 -> s2 -> ... -> sk` such that:
 *
 * - Every adjacent pair of words differs by a single letter.
 * - Every `si` for `1 <= i <= k` is in `wordList`. Note that `beginWord` does not need to be in `wordList`.
 * - sk == endWord
 *
 * Given two words, `beginWord` and `endWord`, and a dictionary `wordList`, return the number of words in
 * the shortest transformation sequence from `beginWord` to `endWord`, or `0` if no such sequence exists.
 *
 * ### Example 1:
 * ```
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
 * Output: 5
 * ```
 * **Explanation**: One shortest transformation sequence is "hit" -> "hot" -> "dot" -> "dog" -> cog", which is 5 words long.
 *
 * ### Example 2:
 * ```
 * Input: beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log"]
 * Output: 0
 * ```
 *
 * **Explanation**: The endWord "cog" is not in wordList, therefore there is no valid transformation sequence.
 *
 * ### Constraints:
 *
 * - 1 <= beginWord.length <= 10
 * - endWord.length == beginWord.length
 * - 1 <= wordList.length <= 5000
 * - wordList[i].length == beginWord.length
 * - beginWord, endWord, and wordList[i] consist of lowercase English letters.
 * - beginWord != endWord
 * - All the words in wordList are unique.
 * */
interface WordLadder {
    fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int

    class Naive : WordLadder {
        override fun ladderLength(
            beginWord: String,
            endWord: String,
            wordList: List<String>
        ): Int {
            val queue = ArrayDeque<Pair<String, Int>>()
            val visited = mutableSetOf<String>()
            queue.addLast(beginWord to 1)


            while (queue.isNotEmpty()) {
                for (i in 0..<queue.size) {
                    val currentPair = queue.removeFirst()
                    val currentWord = currentPair.first
                    if (currentWord == endWord) {
                        return currentPair.second
                    }
                    wordList.forEach { word ->
                        if (isValid(currentWord, word) && !visited.contains(word)) {
                            queue.addLast(word to currentPair.second + 1)
                            visited.add(word)
                        }
                    }
                }
            }

            return 0
        }

        private fun isValid(word1: String, word2: String): Boolean {
            var diffCount = 0
            for (i in 0..<word1.length) {
                if (word1[i] != word2[i]) {
                    diffCount++
                    if (diffCount > 1) return false
                }
            }

            return diffCount == 1
        }
    }

    class Optimized : WordLadder {
        override fun ladderLength(
            beginWord: String,
            endWord: String,
            wordList: List<String>
        ): Int {
            val queue = ArrayDeque<Pair<String, Int>>()
            val wordsSet = wordList.toHashSet()

            queue.addLast(beginWord to 1)

            while (queue.isNotEmpty()) {
                val (currentWord, distance) = queue.removeFirst()
                if (currentWord == endWord) return distance

                val chars = currentWord.toCharArray()
                for (i in chars.indices) {
                    val originalChar = chars[i]
                    for (ch in 'a'..'z') {
                        if (chars[i] == ch) continue

                        chars[i] = ch
                        val nextWord = String(chars)

                        if (wordsSet.contains(nextWord)) {
                            queue.addLast(nextWord to distance + 1)
                            wordsSet.remove(nextWord)
                        }
                    }
                    chars[i] = originalChar
                }
            }

            return 0
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val beginWord = "hit"
            val endWord = "cog"
            val dict = listOf("hot", "dot", "dog", "lot", "log", "cog")
            val dict2 = listOf("hot", "dot", "dog", "lot", "log")

            var solution: WordLadder = Naive()
            println(solution.ladderLength(beginWord, endWord, dict))

            println("----------------")

            solution = Optimized()
            println(solution.ladderLength(beginWord, endWord, dict2))
        }
    }
}