package ru.karpyzin.data.base

interface EntityMapper<E, P> {
    fun toEntity(data: P): E
    fun fromEntity(data: E): P
}