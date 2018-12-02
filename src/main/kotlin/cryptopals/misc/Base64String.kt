package cryptopals.misc

import org.apache.commons.codec.binary.Base64

class Base64String(val bytes: ByteArray) {
    val prettyPrinted: String
        get() = bytes.toString(Charsets.UTF_8)

    val decoded: String
        get() = Base64.decodeBase64(prettyPrinted).toString(Charsets.UTF_8)

    companion object {
        fun fromPretty(prettyString: String): Base64String {
            return Base64String(Base64.decodeBase64(prettyString))
        }
    }
}
