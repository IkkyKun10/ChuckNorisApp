package com.riezki.chucknorisapp.core.remote


import com.squareup.moshi.Json

data class JokesDto(
    @Json(name = "result")
    val result: List<Result>? = null,
    @Json(name = "total")
    val total: Int? = null
)