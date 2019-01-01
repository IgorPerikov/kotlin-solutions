package advent.y2018

import misc.readAdventInput
import java.util.*

fun main(args: Array<String>) {
    println(buildSequenceOfSteps(parseRawRequirements(readAdventInput(7, 2018))))
}

private fun buildSequenceOfSteps(rawRequirements: List<Requirement>): String {
    val resultBuilder = StringBuilder()
    val remainingSteps = TreeSet(rawRequirements.flatMap { listOf(it.prerequisite, it.step) })
    val requirements = parseRequirements(rawRequirements)
    while (remainingSteps.isNotEmpty()) {
        val iterator = remainingSteps.iterator()
        while (iterator.hasNext()) {
            val currentStep = iterator.next()
            val prerequisites = requirements[currentStep]
            if (prerequisites == null || prerequisites.all { !remainingSteps.contains(it) }) {
                iterator.remove()
                resultBuilder.append(currentStep)
                break
            }
        }
    }
    return resultBuilder.toString()
}

private fun parseRequirements(rawRequirements: List<Requirement>): Map<Char, List<Char>> {
    return rawRequirements.groupBy({ it.step }, { it.prerequisite })
}

private fun parseRawRequirements(input: List<String>): List<Requirement> {
    return input.map { Requirement(it.substringAfter("Step ")[0], it.substringAfter("step ")[0]) }
}

data class Requirement(val prerequisite: Char, val step: Char)
