package s100

import java.lang.Integer.max

class Solution198 {
    fun rob(nums: IntArray): Int {
        return when (nums.size) {
            0 -> 0
            1 -> nums[0]
            else -> {
                nums.forEachIndexed { index, element ->
                    if (index == 2) {
                        nums[index] += nums[index - 2]
                    } else if (index > 2) {
                        nums[index] += max(nums[index - 2], nums[index - 3])
                    }
                }
                max(nums[nums.lastIndex], nums[nums.lastIndex - 1])
            }
        }
    }
}
