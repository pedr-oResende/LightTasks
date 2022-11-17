package br.com.lighttasks.commom.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}