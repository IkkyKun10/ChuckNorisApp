package com.riezki.chucknorisapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class JokesDomain(
    val categories: List<String?>? = null,
    val createdAt: String? = null,
    val iconUrl: String? = null,
    val id: String? = null,
    val updatedAt: String? = null,
    val url: String? = null,
    val value: String? = null
) : Parcelable
