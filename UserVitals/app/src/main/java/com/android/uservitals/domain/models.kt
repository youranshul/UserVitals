package com.android.uservitals.domain

data class AllVitals(
    val name: String,
    val dob: String,
    val city: String,
    val vitals: List<Vitals>
)

data class Vitals(val type: String, val unit: String, val date: String, val maxValue: String)

