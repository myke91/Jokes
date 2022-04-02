package com.lillydoo.jokes.data.source.remote

import com.lillydoo.jokes.data.model.JokesDTO
import com.lillydoo.jokes.data.source.JokesListDatasource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class JokesListRemoteDatasource @Inject constructor(private val api: JokesListApi) :
    JokesListDatasource {

    override suspend fun fetchJokes(): Flow<Result<List<JokesDTO>>> {
        val response = api.fetchJokes()

        if (response.isSuccessful) {
            val jokesResponse = response.body()

            if (jokesResponse?.error == true) {
                return flow {
                    emit(Result.failure(RuntimeException("Unable to fetch data from API")))
                }
            }

            return flow {
                emit(Result.success(jokesResponse!!.jokes))
            }
        } else {
            return flow {
                emit(Result.failure(RuntimeException("Unable to fetch data from API")))
            }
        }
    }
}

