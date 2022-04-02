package com.lillydoo.jokes.domain

import com.lillydoo.jokes.data.model.JokeType
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.data.model.JokesDTO
import com.lillydoo.shared.utils.DomainMapper

class JokesMapper : DomainMapper<JokesDTO, Jokes> {

    override fun mapToDomainModel(model: JokesDTO): Jokes {
        return Jokes(
            model.id,
            JokeType.valueOf(model.type.uppercase()),
            model.setup ?: "",
            model.delivery ?: "",
            model.joke ?: ""
        )
    }

    fun toDomainList(initial: List<JokesDTO>): List<Jokes>{
        return initial.map { mapToDomainModel(it) }
    }
}