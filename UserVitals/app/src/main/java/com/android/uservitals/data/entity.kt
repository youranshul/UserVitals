package com.android.uservitals.data

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class UserVitals(
    @JsonProperty("name") val name: String?,
    @JsonProperty("dob") val dob: String?,
    @JsonProperty("city") val city: String?,
    @JsonProperty("vitals") val vitals: List<Vital>? = null
)

@JsonInclude(JsonInclude.Include.NON_NULL)
data class Vital(
    @JsonProperty("type") val type: String,
    @JsonProperty("unit") val unit: String,
    @JsonProperty("dates") val dates: List<String>? = null,
    @JsonProperty("values") val values: List<String>? = null
)