package com.lillydoo.jokes.data.source.remote

import com.lillydoo.jokes.data.model.JokesResponse
import retrofit2.Response
import retrofit2.http.GET

interface JokesListApi {
    @GET("joke/Any?amount=10")
    suspend fun fetchJokes(): Response<JokesResponse>
}
