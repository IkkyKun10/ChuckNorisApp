package com.riezki.chucknorisapp.core.remote


import com.squareup.moshi.Json

data class Result(
    @Json(name = "categories")
    val categories: List<String?>? = null,
    @Json(name = "created_at")
    val createdAt: String? = null,
    @Json(name = "icon_url")
    val iconUrl: String? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "updated_at")
    val updatedAt: String? = null,
    @Json(name = "url")
    val url: String? = null,
    @Json(name = "value")
    val value: String? = null
)