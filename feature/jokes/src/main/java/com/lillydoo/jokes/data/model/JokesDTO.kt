package com.lillydoo.jokes.data.model

data class JokesDTO(
    val id: Int,
    val safe: Boolean,
    val lang: String,
    val category: String,
    val type: String,
    val setup: String?,
    val delivery: String?,
    val joke: String?,
    val flags: JokeFlags

)