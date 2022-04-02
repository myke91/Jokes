package com.lillydoo.jokes.data.model

data class Jokes(
    val id: Int,
    val type: JokeType,
    val setup: String,
    val delivery: String,
    val joke: String,
)