package strings

interface EncodeDecodeStrings {

    fun encode(strs: List<String>): String
    fun decode(str: String): List<String>

    class Solution : EncodeDecodeStrings {

        override fun encode(strs: List<String>): String {
            val resultBuilder = StringBuilder()
            strs.forEach { str ->
                resultBuilder.append(str.length)
                    .append('#')
                    .append(str)
            }
            return resultBuilder.toString()
        }

        override fun decode(str: String): List<String> {
            val result = mutableListOf<String>()
            var strPointer = 0
            while (strPointer < str.length) {
                var numberPointer = strPointer
                while (str[numberPointer] != '#') numberPointer++

                val length = str.substring(strPointer, numberPointer).toInt()
                val string = str.substring(
                    numberPointer + 1,
                    numberPointer + 1 + length
                )

                result.add(string)
                strPointer = numberPointer + 1 + length
            }
            return result
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val solution: EncodeDecodeStrings = Solution()
            val exampleInput1 = listOf("Yurii", "Wants", "To", "Pass", "Lab126", "Google", "Meta#1", "Interviews")
            val encoded1 = solution.encode(exampleInput1)
            println(encoded1)
            println(solution.decode(encoded1))
        }
    }
}