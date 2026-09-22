package tree

/**
 * A solution to the LeetCode question [#101](https://leetcode.com/problems/symmetric-tree/description/)
 *
 * Description:
 *
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 *
 * ### Example 1:
 * ```
 * Input: root = [1,2,2,3,4,4,3]
 * Output: true
 * ```
 *
 * ### Example 2:
 * ```
 * Input: root = [1,2,2,null,3,null,3]
 * Output: false
 * ```
 *
 * ### Constraints:
 *
 * - The number of nodes in the tree is in the range [1, 1000].
 * - -100 <= Node.val <= 100
 * */
interface SymmetricTree<T> {

    fun isSymmetric(root: TreeNode<T>?): Boolean

    class Solution<T> : SymmetricTree<T> {

        override fun isSymmetric(root: TreeNode<T>?): Boolean {
            if (root == null) return false
            return isMirror(root.left, root.right)
        }

        private fun isMirror(l: TreeNode<T>?, r: TreeNode<T>?): Boolean {
            if (r == null && l == null) return true
            if (r == null || l == null) return false
            if (r.`val` != l.`val`) return false

            return isMirror(l.left, r.right) && isMirror(l.right, r.left)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val example = arrayOf<Int?>(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15).asTree()
            val solution: SymmetricTree<Int?> = Solution()
            println("------FALSE-------")
            println(example.toString())
            println("------------------")
            println(solution.isSymmetric(example))

            val example2 = arrayOf<Int?>(1, 2, 2, 3, 4, 4, 3, 5, 6, 7, 8, 8, 7, 6, 5).asTree()
            println("----- TRUE--------")
            println(example2.toString())
            println("------------------")
            println(solution.isSymmetric(example2))
        }
    }
}