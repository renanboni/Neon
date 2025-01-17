package com.boni.data.mapper

interface ModelMapper<in M, out E> {
    fun mapFromModel(model: M) : E
}