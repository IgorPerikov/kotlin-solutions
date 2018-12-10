package misc

import java.io.File

fun readAdventInput(day: Int, year: Int): List<String> {
    return readInput("advent/y$year/day$day")
}

fun readCryptopalsInput(set: Int, challenge: Int): List<String> {
    return readInput("cryptopals/set$set/$challenge")
}

fun readInput(fileName: String): List<String> {
    return File("src/main/resources/$fileName").readLines().map { it.trim() }.filter { it.isNotEmpty() }
}
