package br.com.lighttasks.commom.mapper

interface NullableMapper<I, O> {
    fun map(input: I?): O?
}