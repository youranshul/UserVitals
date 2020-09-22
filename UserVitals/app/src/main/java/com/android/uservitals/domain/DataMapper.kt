package com.android.uservitals.domain

interface DataMapper<RESPONSE, MODEL> {
    fun transform(data: RESPONSE): MODEL
}