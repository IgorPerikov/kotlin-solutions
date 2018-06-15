package s200

class Solution268 {
    fun missingNumber(nums: IntArray): Int {
        val result = (nums.size + 1) * nums.size / 2
        return nums.fold(result) { acc, i -> acc - i }
    }
}
