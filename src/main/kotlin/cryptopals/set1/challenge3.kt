package cryptopals.set1

import cryptopals.misc.HexString
import java.io.File
import kotlin.experimental.xor

fun singleByteXor(input: ByteArray, key: Byte): ByteArray {
    input.forEachIndexed { index, byte ->
        input[index] = byte.xor(key)
    }
    return input
}

fun singleByteXor(input: HexString, key: Byte): HexString {
    return HexString(singleByteXor(input.bytes, key))
}

fun decipherSingleByteXor(encryptedMessage: HexString): String {
    return sortXorDecryptedMessagesByScore((Byte.MIN_VALUE..Byte.MAX_VALUE)
        .map {
            singleByteXor(encryptedMessage, it.toByte()).prettyPrinted
        })
        .first()
}

fun decipherSingleByteXor(encryptedMessage: HexString, take: Int): List<String> {
    return sortXorDecryptedMessagesByScore((Byte.MIN_VALUE..Byte.MAX_VALUE)
        .map {
            singleByteXor(encryptedMessage, it.toByte()).prettyPrinted
        })
        .take(take)
}

fun decipherSingleByteXor(encryptedMessage: String) = decipherSingleByteXor(HexString.fromPretty(encryptedMessage))

fun sortXorDecryptedMessagesByScore(decryptedMessages: List<String>): List<String> {
    val charFrequenciesMap = buildCharFrequenciesMap()
    return decryptedMessages.sortedByDescending { calculateScore(it, charFrequenciesMap) }
}

private fun calculateScore(input: String, frequencies: Map<Char, Double>): Double {
    var score = 1.0
    for (char in input) {
        val frequency = frequencies[char.toLowerCase()] ?: 1e-10
        score *= frequency
    }
    return score
}

private fun buildCharFrequenciesMap(): Map<Char, Double> {
    val charFrequencies = mutableMapOf<Char, Double>()
    for (book in File("src/main/resources/cryptopals/set1/books/").listFiles()) {
        var skip = true
        for (line in book.readLines()) {
            if (line.startsWith("*** START OF THIS PROJECT GUTENBERG EBOOK")) {
                skip = false
            }
            if (skip) {
                continue
            }
            if (line.startsWith("*** END OF THIS PROJECT GUTENBERG EBOOK")) {
                break
            }
            for (char in line) {
                if (char.isLetterOrDigit() || char == ' ') {
                    charFrequencies.merge(char.toLowerCase(), 1.0) { old, new -> old + new }
                }
            }
        }
    }
    normalizeFrequencies(charFrequencies)
    return charFrequencies
}

private fun <T> normalizeFrequencies(frequencies: MutableMap<T, Double>) {
    val max = frequencies.values.max() ?: throw IllegalArgumentException("No letters found in books")
    frequencies.forEach { key, value ->
        frequencies[key] = value / max
    }
}
