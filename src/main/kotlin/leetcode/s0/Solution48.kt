package leetcode.s0

class Solution48 {
    fun rotate(matrix: Array<IntArray>): Unit {
        for (i in 0 until matrix.size / 2) {
            for (j in i..matrix.size - i - 2) {
                var valueToMove = matrix[i][j]
                var newI = i
                var newJ = j
                for (a in 1..4) {
                    val newCoordinates = whereToMove(matrix, newI, newJ)
                    newI = newCoordinates.first
                    newJ = newCoordinates.second
                    val removedValue: Int = matrix[newI][newJ]
                    matrix[newI][newJ] = valueToMove
                    valueToMove = removedValue
                    println("$i$j next we're moving $valueToMove")
                }
            }
        }
    }

    private fun whereToMove(matrix: Array<IntArray>, i: Int, j: Int): Pair<Int, Int> {
        return Pair(j, matrix.size - i - 1)
    }
}
