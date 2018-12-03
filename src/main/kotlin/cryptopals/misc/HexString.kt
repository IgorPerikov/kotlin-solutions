package cryptopals.misc

import org.apache.commons.codec.binary.Hex

class HexString(val bytes: ByteArray) {
    val prettyPrinted: String
        get() = bytes.toString(Charsets.UTF_8)

    companion object {
        fun fromPretty(prettyString: String): HexString {
            return HexString(Hex.decodeHex(prettyString))
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HexString

        if (!bytes.contentEquals(other.bytes)) return false
        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}
