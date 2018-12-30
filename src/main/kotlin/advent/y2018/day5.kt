package advent.y2018

import misc.readAdventInput
import java.util.*

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
    val indexesOfRemainedPolymers: TreeSet<Int> = TreeSet()
    for (i in 0 until polymers.length) {
        indexesOfRemainedPolymers.add(i)
    }
    var left = 0
    var right = 1
    var reactions = 0
    while (true) {
        if (left >= polymers.length || right >= polymers.length) {
            break
        }
        if (shouldReact(polymers[left], polymers[right])) {
            reactions++
            indexesOfRemainedPolymers.remove(left)
            indexesOfRemainedPolymers.remove(right)
            val closestOneToTheLeft = getClosestOneToTheLeft(left, indexesOfRemainedPolymers)
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

private fun getClosestOneToTheLeft(left: Int, indexesOfRemainedPolymers: TreeSet<Int>): Int? {
    return indexesOfRemainedPolymers.floor(left - 1)
}

private fun shouldReact(a: Char, b: Char): Boolean {
    return (a.isLowerCase() && b.isUpperCase() && a.toUpperCase() == b) ||
            (b.isLowerCase() && a.isUpperCase() && b.toUpperCase() == a)
}
