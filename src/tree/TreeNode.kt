package tree

class TreeNode<T>(var `val`: T) {
    var left: TreeNode<T>? = null
    var right: TreeNode<T>? = null

    override fun toString(): String {
        return StringBuilder().printTreeDiagram(this).toString()
    }

    private fun <T> StringBuilder.printTreeDiagram(
        node: TreeNode<T>?,
        top: String = "",
        root: String = "",
        bottom: String = ""
    ): StringBuilder {

        if (node?.left == null && node?.right == null) {
            appendLine("$root${node?.`val`}")
            return this
        } else {
            printTreeDiagram(node.right, "$top    ", "$top┌── ", "$top│   ")
            appendLine("$root${node.`val`}")
            printTreeDiagram(node.left, "$bottom│   ", "$bottom└── ", "$bottom    ")
            return this
        }
    }
}