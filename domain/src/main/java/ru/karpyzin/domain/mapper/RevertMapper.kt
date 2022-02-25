package ru.karpyzin.domain.mapper

interface RevertMapper<I, O>: Mapper<I, O> {
    fun revert(data: O): I
    fun revertList(data: List<O>): List<I> = data.map { revert(it) }
}