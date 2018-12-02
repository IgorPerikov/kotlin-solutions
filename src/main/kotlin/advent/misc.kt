package advent

import java.io.File

fun readInput(day: Int, year: Int): List<String> {
    return File("src/main/resources/advent/y$year/day$day").readLines().map { it.trim() }.filter { it.isNotEmpty() }
}
