package tree

/**
 * A solution to the LeetCode problem #100:
 *
 * */

interface SameTree<T> {
    fun isSameTree(p: TreeNode<T>?, q: TreeNode<T>?): Boolean

    class Recursion<T> : SameTree<T> {
        override fun isSameTree(p: TreeNode<T>?, q: TreeNode<T>?): Boolean {
            if (p == null && q == null) return true
            if (p == null || q == null) return false
            if (p.`val` != q.`val`) return false
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right)
        }
    }

    class StackBased<T> : SameTree<T> {
        override fun isSameTree(p: TreeNode<T>?, q: TreeNode<T>?): Boolean {
            val stack = ArrayDeque<Pair<TreeNode<T>?, TreeNode<T>?>>()
            stack.addLast(p to q)
            while (!stack.isEmpty()) {
                val pair = stack.removeLast()
                val p = pair.first
                val q = pair.second
                if (p == null && q == null) continue
                if (p == null || q == null) return false
                if (p.`val` != q.`val`) return false
                stack.addLast(p.left to q.left)
                stack.addLast(p.right to q.right)
            }
            return true
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val exampleL = arrayOf<Int?>(1, 2, 2, 3, 4, 3, 4).asTree()
            val exampleR = arrayOf<Int?>(1, 2, 2, 3, 4, 3, 4).asTree()

            val example2L = arrayOf<Int?>(1, 2).asTree()
            val example2R = arrayOf(1, null, 2).asTree()

            println(exampleL.toString())
            println("--------------\n")
            println(exampleR.toString())

            var solution: SameTree<Int?> = Recursion()
            println(solution.isSameTree(exampleL, exampleR))

            println("---- STACK BASED ----\n")
            solution = StackBased()
            println(example2L.toString())
            println(example2R.toString())
            println(solution.isSameTree(example2L, example2R))
            println("---------------\n")
        }
    }
}