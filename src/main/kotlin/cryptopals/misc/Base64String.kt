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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Base64String

        if (!bytes.contentEquals(other.bytes)) return false
        return true
    }

    override fun hashCode(): Int {
        return bytes.contentHashCode()
    }
}
