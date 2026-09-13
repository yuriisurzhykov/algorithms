package strings

/**
 * The solution to the LeetCode coding question #49: https://leetcode.com/problems/group-anagrams/
 * */
interface GroupAnagrams {

    fun groupAnagrams(group: Array<String>): List<List<String>>

    /**
     * The complexity of the given algorithm is O(N x K*logK), where N - number of strings,
     * and K number of letters in each string.
     * The K*logK appears because each string we try to sort, and the sorting complexity is N*logN.
     * */
    class Sorted : GroupAnagrams {
        override fun groupAnagrams(group: Array<String>): List<List<String>> {
            val map = HashMap<String, ArrayList<String>>()
            group.forEach { str ->
                val sorted = str.sorted()
                map.getOrPut(sorted) { ArrayList() }.add(str)
            }
            return map.values.toList()
        }
    }

    /**
     * The complexity of the given algorithm is O(N x K), where N - number of strings,
     * and K number of letters in each string.
     * We reduce the sorting complexity by using a counter-array, which gives us a plain O(K)
     * on each iteration.
     * */
    class Count : GroupAnagrams {
        override fun groupAnagrams(group: Array<String>): List<List<String>> {
            val map = HashMap<String, ArrayList<String>>()
            group.forEach { str ->
                val chars = Array(26) { 'a' + it }
                str.forEach { ch ->
                    chars[ch - 'a']++
                }
                map.getOrPut(chars.joinToString("")) { ArrayList() }.add(str)
            }

            return ArrayList(map.values)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
            val solution: GroupAnagrams = Count()
            println(solution.groupAnagrams(strs))
        }
    }
}