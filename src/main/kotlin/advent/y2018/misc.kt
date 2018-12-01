package advent.y2018

import java.io.File

fun readInput(day: Int): List<String> {
    return File("src/main/resources/day$day").readLines().map { it.trim() }.filter { it.isNotEmpty() }
}
