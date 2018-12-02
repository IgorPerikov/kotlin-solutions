package advent.y2018

import advent.readInput

/**
 * https://adventofcode.com/2018/day/1
 */
fun main(args: Array<String>) {
    partA().also { println(it) }
    partB().also { println(it) }
}

private fun readAsInts(): List<Int> = readInput(1, 2018).map(Integer::valueOf)

private fun partA() = readAsInts().sum()

private fun partB(): Long {
    val frequencyChanges = readAsInts()
    var currentFrequency = 0L
    val frequencies = mutableSetOf(currentFrequency)
    while (true) {
        for (i in 0 until frequencyChanges.size) {
            currentFrequency += frequencyChanges[i]
            if (frequencies.contains(currentFrequency)) {
                return currentFrequency
            }
            frequencies.add(currentFrequency)
        }
    }
}
