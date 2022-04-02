package com.lillydoo.jokes.data.model

data class JokesResponse (
    val error: Boolean,
    val amount: Int,
    val jokes: List<JokesDTO>
)