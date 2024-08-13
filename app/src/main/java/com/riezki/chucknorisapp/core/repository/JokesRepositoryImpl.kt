package com.riezki.chucknorisapp.core.repository

import android.util.Log
import com.riezki.chucknorisapp.core.mapper.toDomain
import com.riezki.chucknorisapp.core.mapper.toJokesEntity
import com.riezki.chucknorisapp.core.service.ApiService
import com.riezki.chucknorisapp.domain.model.JokesDomain
import com.riezki.chucknorisapp.domain.repository.JokesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * @author riezkymaisyar
 */

class JokesRepositoryImpl(
    private val apiService: ApiService
) : JokesRepository {
    override fun getJokes(query: String): Flow<List<JokesDomain>> {
        return flow {
            try {
                val response = apiService.getJokes(query)
                val jokesEntity = response.result?.map { it.toJokesEntity() }
                val domain = jokesEntity?.map { it.toDomain() }
                domain?.let { emit(it) }
            } catch (e: HttpException) {
                Log.i(TAG, "getJokes: Failure on HttpException")
                emit(listOf(JokesDomain(value = "Data is HttpException")))
                e.printStackTrace()
            } catch (e: SocketTimeoutException) {
                Log.i(TAG, "getJokes: Failure on SocketTimeoutException")
                emit(listOf(JokesDomain(value = "Data is SocketTimeoutException")))
                e.printStackTrace()
            } catch (e: Exception) {
                Log.i(TAG, "getJokes: Failure on Exception")
                emit(listOf(JokesDomain(value = "Data is Exception")))
                e.printStackTrace()
            }
        }
    }

    companion object {
        const val TAG = "JokesRepositoryImpl"
    }
}