package arrays

class DuplicateValues {

    fun duplicateValues(nums: IntArray): Boolean {
        return nums.toSet().size != nums.size
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(1, 2, 3, 1)
            val solution = DuplicateValues()
            println(solution.duplicateValues(nums))
        }
    }
}