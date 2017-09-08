package uk.co.garyhomewood.marvelcomics.extensions

import java.security.MessageDigest

/**
 * Created by homewoodg on 04/09/2017.
 */

fun String.MD5(): String {
    val md: MessageDigest = MessageDigest.getInstance("MD5")
    md.update(this.toByteArray(Charsets.UTF_8))
    val digest = md.digest()
    val sb = StringBuilder()
    for (byte in digest) sb.append("%02x".format(byte))
    return sb.toString()
}