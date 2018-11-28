package leetcode.s100

import leetcode.other.TreeNode
import java.util.*

class Solution101IterativeDfs {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        val leftNodes: Deque<TreeNode> =
            ArrayDeque<TreeNode>().also { it.addFirst(root.left ?: TreeNode(Int.MIN_VALUE)) }
        val rightNodes: Deque<TreeNode> =
            ArrayDeque<TreeNode>().also { it.addFirst(root.right ?: TreeNode(Int.MIN_VALUE)) }
        while (!leftNodes.isEmpty() && !rightNodes.isEmpty()) {
            val leftNode = leftNodes.poll()
            val rightNode = rightNodes.poll()
            if (leftNode.`val` != rightNode.`val`) return false

            if ((leftNode.left == null) != (rightNode.right == null)) return false
            if ((leftNode.right == null) != (rightNode.left == null)) return false

            if (leftNode.left != null) leftNodes.push(leftNode.left)
            if (leftNode.right != null) leftNodes.push(leftNode.right)

            if (rightNode.right != null) rightNodes.push(rightNode.right)
            if (rightNode.left != null) rightNodes.push(rightNode.left)
        }
        return true
    }
}

class Solution101IterativeBfs {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        val leftNodes: Queue<TreeNode> =
            LinkedList<TreeNode>().apply { this.offer(root.left ?: TreeNode(Int.MAX_VALUE)) }
        val rightNodes: Queue<TreeNode> =
            LinkedList<TreeNode>().apply { this.offer(root.right ?: TreeNode(Int.MAX_VALUE)) }
        while (!leftNodes.isEmpty() && !rightNodes.isEmpty()) {
            val leftNode = leftNodes.poll()
            val rightNode = rightNodes.poll()
            if (leftNode.`val` != rightNode.`val`) return false
            if ((leftNode.left == null) != (rightNode.right == null)) return false
            if ((leftNode.right == null) != (rightNode.left == null)) return false

            if (leftNode.left != null) leftNodes.offer(leftNode.left)
            if (leftNode.right != null) leftNodes.offer(leftNode.right)

            if (rightNode.right != null) rightNodes.offer(rightNode.right)
            if (rightNode.left != null) rightNodes.offer(rightNode.left)
        }
        return true
    }
}

class Solution101Recursive {
    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isEqual(root.left, root.right)
    }

    fun isEqual(left: TreeNode?, right: TreeNode?): Boolean {
        return if (left != null && right != null) {
            if (left.`val` != right.`val`) return false
            val equal1 = isEqual(left.left, right.right)
            val equal2 = isEqual(left.right, right.left)
            equal1 && equal2
        } else if (left == null && right != null) {
            false
        } else if (left != null && right == null) {
            false
        } else {
            true
        }
    }
}
