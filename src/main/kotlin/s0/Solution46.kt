package s0

class Solution46 {
    fun permute(nums: IntArray): List<List<Int>> {
        val permutations = mutableListOf<List<Int>>()
        for (num in nums) {
            permutations.addAll(genPerm(listOf(num), nums.toMutableList().also { it.remove(num) }))
        }
        return permutations
    }

    private fun genPerm(currentSequence: List<Int>, unusedDigits: List<Int>): List<List<Int>> {
        if (unusedDigits.isEmpty()) {
            return listOf(currentSequence)
        }
        val subPermutations = mutableListOf<List<Int>>()
        for (unusedDigit in unusedDigits) {
            subPermutations.addAll(
                genPerm(
                    currentSequence.toMutableList().also { it.add(unusedDigit) },
                    unusedDigits.toMutableList().also { it.remove(unusedDigit) }
                )
            )
        }
        return subPermutations
    }
}

object Some {
    @JvmStatic
    fun main(args: Array<String>) {
        println(Solution46().permute(intArrayOf(1, 2, 3, 4)))
    }
}
