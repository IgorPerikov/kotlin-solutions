package cryptopals.set1

import cryptopals.misc.Base64String
import cryptopals.misc.HexString
import org.apache.commons.codec.binary.Base64

fun convertHexToBase64(hexString: HexString): Base64String {
    return Base64String(Base64.encodeBase64(hexString.bytes))
}
