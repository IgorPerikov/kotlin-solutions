package cryptopals.set1

import cryptopals.misc.HexString
import kotlin.experimental.xor

fun fixedXor(first: ByteArray, second: ByteArray): ByteArray {
    if (first.size != second.size) {
        throw IllegalArgumentException("Byte arrays should be of the same size")
    }
    return ByteArray(first.size) { i -> first[i].xor(second[i]) }
}

fun fixedXor(first: HexString, second: HexString): HexString {
    return HexString(fixedXor(first.bytes, second.bytes))
}
