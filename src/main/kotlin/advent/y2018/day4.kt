package advent.y2018

import advent.readInput
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * https://adventofcode.com/2018/day/3
 */
private typealias GuardId = Int

private typealias Minute = Int
private typealias Count = Int

fun main(args: Array<String>) {
    val guardToIntervalsMap = buildGuardToIntervalsMap()
    whoSleepTheMostTotal(guardToIntervalsMap).also { println(it) }
    whoSleepTheMostAtAnyMinute(guardToIntervalsMap).also { println(it) }
}

private fun whoSleepTheMostTotal(guardToIntervalsMap: Map<GuardId, List<Interval>>): Int {
    val guardWhoSleepTheMost = guardToIntervalsMap.maxBy { it.value.sumBy { it.end - it.start } }!!
    val totalSleepDistribution = mutableMapOf<Minute, Int>()
    for (interval in guardWhoSleepTheMost.value) {
        for (min in interval.start until interval.end) {
            totalSleepDistribution.merge(min, 1) { old, new -> old + new }
        }
    }
    val guardId = guardWhoSleepTheMost.key
    val mostPopularMinute = totalSleepDistribution.maxBy { entry -> entry.value }!!.key
    return mostPopularMinute * guardId
}

private fun whoSleepTheMostAtAnyMinute(guardToIntervalsMap: Map<GuardId, List<Interval>>): Int {
    val distribution = mutableMapOf<Minute, MutableMap<GuardId, Count>>()
    for ((guardId, intervals) in guardToIntervalsMap) {
        for (interval in intervals) {
            for (min in interval.start until interval.end) {
                distribution
                    .computeIfAbsent(min) { mutableMapOf() }
                    .merge(guardId, 1) { old, new -> old + new }
            }
        }
    }
    var max = 0
    var guard = 0
    var maxMinute = 0
    for (minute in distribution.keys) {
        val mostFrequentGuard = distribution[minute]!!.maxBy { entry -> entry.value }!!.key
        val frequency = distribution[minute]!!.values.max()!!
        if (frequency > max) {
            max = frequency
            guard = mostFrequentGuard
            maxMinute = minute
        }
    }
    return maxMinute * guard
}

private fun buildGuardToIntervalsMap(): Map<GuardId, List<Interval>> {
    val guardEvents: List<GuardEvent> = readInput(4, 2018)
        .map(::parseEvent)
        .sortedBy { it.eventDateTime }
    val guardToIntervalsMap = mutableMapOf<GuardId, MutableList<Interval>>()
    var currentGuardId: GuardId = 0
    var currentIntervalStart: Minute = 0
    for (i in 0 until guardEvents.size) {
        val guardEvent = guardEvents[i]
        val guardId = guardEvent.getGuardId()
        when {
            guardId != null -> {
                currentGuardId = guardId
            }
            guardEvent.isFallsAsleep() -> {
                currentIntervalStart = guardEvent.minute
            }
            guardEvent.isAwake() -> {
                val interval = Interval(currentIntervalStart, guardEvent.minute)
                guardToIntervalsMap.computeIfAbsent(currentGuardId) { mutableListOf() }.add(interval)
            }
        }
    }
    return guardToIntervalsMap
}

private fun parseEvent(event: String): GuardEvent {
    return GuardEvent(
        LocalDateTime.parse(event.substringAfter("[").substringBefore("]"), dtf),
        event.substringAfter("] ")
    )
}

private val dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

private data class Interval(val start: Minute, val end: Minute)

private data class GuardEvent(val eventDateTime: LocalDateTime, val message: String) {
    fun getGuardId(): GuardId? {
        return if (message.contains("#")) {
            message.substringAfter("#").substringBefore(" ").toInt()
        } else {
            null
        }
    }

    fun isFallsAsleep(): Boolean = message == "falls asleep"

    fun isAwake(): Boolean = message == "wakes up"

    val minute: Minute = eventDateTime.minute
}
