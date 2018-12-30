package advent.y2018

import misc.readAdventInput

fun main(args: Array<String>) {
    val polymersChain = readAdventInput(5, 2018)[0]
    println(remainUnitsAfterAllReactions(polymersChain))
    println(leastPossiblePolymerChainAfterRemovingAny(polymersChain))
}

private fun leastPossiblePolymerChainAfterRemovingAny(polymers: String): Int {
    val allChars = polymers.toCharArray().map { it.toLowerCase().toString() }.distinct()
    return allChars
        .map { removedChar ->
            remainUnitsAfterAllReactions(polymers.replace(removedChar, "", ignoreCase = true))
        }
        .min()!!
}

private fun remainUnitsAfterAllReactions(polymers: String): Int {
    val polymerExistence = IntArray(polymers.length) { 1 }
    var left = 0
    var right = 1
    var reactions = 0
    while (true) {
        if (left >= polymers.length || right >= polymers.length) {
            break
        }
        if (shouldReact(polymers[left], polymers[right])) {
            reactions++
            polymerExistence[left] = 0
            polymerExistence[right] = 0
            val closestOneToTheLeft = getClosestOneToTheLeft(left, polymerExistence)
            if (closestOneToTheLeft == null) {
                left = right + 1
                right += 2
            } else {
                left = closestOneToTheLeft
                right++
            }
        } else {
            left = right
            right++
        }
    }
    return polymers.length - reactions * 2
}

// TODO: calling this function each time is suboptimal
private fun getClosestOneToTheLeft(left: Int, polymerExistence: IntArray): Int? {
    for (index in left - 1 downTo 0) {
        if (polymerExistence[index] == 1) {
            return index
        }
    }
    return null
}

private fun shouldReact(a: Char, b: Char): Boolean {
    return (a.isLowerCase() && b.isUpperCase() && a.toUpperCase() == b) ||
            (b.isLowerCase() && a.isUpperCase() && b.toUpperCase() == a)
}
