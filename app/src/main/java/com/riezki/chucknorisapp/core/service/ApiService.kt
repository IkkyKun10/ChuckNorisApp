package com.riezki.chucknorisapp.core.service

import com.riezki.chucknorisapp.core.remote.JokesDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author riezkymaisyar
 */

interface ApiService {

    @GET("jokes/search?")
    suspend fun getJokes(
        @Query("query") query: String = "animal"
    ) : JokesDto

    companion object {
        const val BASE_URL = "https://api.chucknorris.io/"
    }
}