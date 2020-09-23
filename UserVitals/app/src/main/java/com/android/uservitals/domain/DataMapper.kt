package com.android.uservitals.domain

interface DataMapper<RESPONSE, MODEL> {
    fun transform(data: RESPONSE): MODEL
}

interface ExtendedMapper<T, R, M> : DataMapper<R, M> {

    fun setValue(option: T)
}