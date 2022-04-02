package com.lillydoo.jokes.data

import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.data.source.remote.JokesListRemoteDatasource
import com.lillydoo.jokes.domain.JokesMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.lang.RuntimeException
import javax.inject.Inject


class JokesListRepository @Inject constructor(
    private val remoteDatasource: JokesListRemoteDatasource,
    private val mapper: JokesMapper
) {
    suspend fun getJokesList(): Flow<Result<List<Jokes>>> = remoteDatasource.fetchJokes().map {
        if (it.isSuccess) {
            Result.success(mapper.toDomainList(it.getOrDefault(emptyList())))
        } else {
            Result.failure(it.exceptionOrNull() ?: RuntimeException("Something went wrong"))
        }
    }
}
