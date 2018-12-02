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
}
