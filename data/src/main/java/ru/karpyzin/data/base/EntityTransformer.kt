package ru.karpyzin.data.base

interface EntityTransformer<E, P> {
    fun toEntity(data: P): E
    fun fromEntity(data: E): P
}