package br.com.lighttasks.commom.extensions

import org.apache.commons.lang3.StringUtils

fun CharSequence.containsIgnoringAccent(
    other: CharSequence,
    ignoreCase: Boolean = false
): Boolean {
    val normalizedString = StringUtils.stripAccents(this.toString()).lowercase()
    return normalizedString.contains(other, ignoreCase = ignoreCase)
}

infix fun String.notEquals(other: String) = this != other

infix fun String.equals(other: String) = this == other