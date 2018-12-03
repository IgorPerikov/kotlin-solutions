package cryptopals

import cryptopals.misc.HexString
import cryptopals.set1.convertHexToBase64
import cryptopals.set1.fixedXor
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * https://cryptopals.com/sets/1
 */
class Set1Suite {
    @Test
    fun `Convert hex to base64`() {
        val base64String = convertHexToBase64(
            HexString.fromPretty(
                "49276d206b696c6c696e6720796f757220627261696e206c696b65206120706f69736f6e6f7573206d757368726f6f6d"
            )
        )
        println(base64String.decoded)
        assertEquals(
            base64String.prettyPrinted,
            "SSdtIGtpbGxpbmcgeW91ciBicmFpbiBsaWtlIGEgcG9pc29ub3VzIG11c2hyb29t"
        )
    }

    @Test
    fun `Fixed xor`() {
        assertEquals(
            fixedXor(
                HexString.fromPretty("1c0111001f010100061a024b53535009181c"),
                HexString.fromPretty("686974207468652062756c6c277320657965")
            ),
            HexString.fromPretty("746865206b696420646f6e277420706c6179")
        )
    }
}
