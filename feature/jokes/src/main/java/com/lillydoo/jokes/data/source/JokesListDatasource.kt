package com.lillydoo.jokes.data.source

import com.lillydoo.jokes.data.model.JokesDTO
import kotlinx.coroutines.flow.Flow

interface JokesListDatasource {
    suspend fun fetchJokes(): Flow<Result<List<JokesDTO>>>
}