package ru.karpyzin.domain.mapper

interface Mapper<I, O> {
    fun convert(data: I): O
    fun convertList(data: List<I>): List<O> = data.map { convert(it) }
}