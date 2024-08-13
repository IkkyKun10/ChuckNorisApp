package com.riezki.chucknorisapp.core.mapper

import com.riezki.chucknorisapp.core.local.JokesEntity
import com.riezki.chucknorisapp.core.remote.Result
import com.riezki.chucknorisapp.domain.model.JokesDomain

/**
 * @author riezkymaisyar
 */

fun Result.toJokesEntity() : JokesEntity {
    return JokesEntity(
        categories = categories,
        createdAt = createdAt,
        iconUrl = iconUrl,
        id = id,
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}

fun JokesEntity.toDomain() : JokesDomain {
    return JokesDomain(
        categories = categories,
        createdAt = createdAt,
        iconUrl = iconUrl,
        id = id,
        updatedAt = updatedAt,
        url = url,
        value = value
    )
}