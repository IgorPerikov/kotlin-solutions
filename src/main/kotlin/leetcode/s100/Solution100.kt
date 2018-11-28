package leetcode.s100

import leetcode.other.TreeNode

class Solution100 {
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        return if (p == null && q != null) {
            false
        } else if (p != null && q == null) {
            false
        } else if (p == null && q == null) {
            true
        } else if (p?.`val` != q?.`val`) {
            false
        } else {
            isSameTree(p?.left, q?.left) && isSameTree(p?.right, q?.right)
        }
    }
}
