package ru.karpyzin.data.base

interface BaseTransformer<T, R> {
    fun transform(data: T): R
}