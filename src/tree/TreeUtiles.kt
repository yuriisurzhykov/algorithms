package tree

fun <T> Array<T>.asTree(): TreeNode<T>? {
    if (isEmpty()) return null
    val rootNode = TreeNode(this[0])
    var index = 1
    val queue = ArrayDeque<TreeNode<T>?>()
    queue.addLast(rootNode)
    while (index < size && queue.isNotEmpty()) {
        val currentNode = queue.removeFirst() ?: continue
        currentNode.left = TreeNode(this[index++])
        if (index < size) {
            currentNode.right = TreeNode(this[index++])
        }
        queue.addLast(currentNode.left)
        queue.addLast(currentNode.right)
    }
    return rootNode
}
