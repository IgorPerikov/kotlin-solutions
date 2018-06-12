package s400

class Solution485 {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var current = 0
        for (num in nums) {
            when (num) {
                1 -> current++
                0 -> { max = Math.max(max, current); current = 0 }
            }
        }
        return Math.max(max, current)
    }
}
