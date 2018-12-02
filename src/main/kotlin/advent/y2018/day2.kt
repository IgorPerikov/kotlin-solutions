package advent.y2018

/**
 * https://adventofcode.com/2018/day/2
 */
fun main(args: Array<String>) {
    val ids = readInput(2)
    calculateChecksum(ids).also { println(it) }
    commonCharactersOfBoxes(ids).also { println(it) }
}

private fun calculateChecksum(ids: List<String>): Int {
    var doubleCount = 0
    var tripleCount = 0
    ids.forEach { id ->
        val lettersCounts = collectCharactersToMap(id)
        lettersCounts.values.count { it == 2 }.let { if (it != 0) doubleCount++ }
        lettersCounts.values.count { it == 3 }.let { if (it != 0) tripleCount++ }
    }
    return doubleCount * tripleCount
}

private fun collectCharactersToMap(id: String): Map<Char, Int> {
    val lettersCounts = mutableMapOf<Char, Int>()
    id.forEach { letter ->
        lettersCounts.merge(letter, 1) { prev, new -> prev + new }
    }
    return lettersCounts
}

private fun commonCharactersOfBoxes(ids: List<String>): String {
    val (id1, id2) = findSimilarBoxes(ids)
    return id1.zip(id2)
        .filter { chars -> chars.first == chars.second }
        .joinToString(separator = "") { chars -> chars.first.toString() }
}

private fun findSimilarBoxes(ids: List<String>): Pair<String, String> {
    ids.forEachIndexed { i1, id1 ->
        ids.forEachIndexed { i2, id2 ->
            if (i1 != i2) {
                var diffs = 0
                var complete = true
                id1.forEachIndexed inner@{ index, char ->
                    if (id2[index] != char) {
                        if (diffs == 1) {
                            complete = false
                            return@inner
                        } else {
                            diffs++
                        }
                    }
                }
                if (complete) {
                    return Pair(id1, id2)
                }
            }
        }
    }
    throw IllegalArgumentException("no similar boxes")
}
