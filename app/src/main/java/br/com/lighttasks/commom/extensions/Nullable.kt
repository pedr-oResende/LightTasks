package br.com.lighttasks.commom.extensions

infix fun <T> T?.ifNull(other: T): T = this ?: other