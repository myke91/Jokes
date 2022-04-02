package com.lillydoo.jokes.interactors

import com.lillydoo.jokes.data.JokesListRepository
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.shared.usecasetypes.BaseUseCaseWithoutParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJokesUseCase @Inject constructor(
    private val repository: JokesListRepository
) : BaseUseCaseWithoutParams<Flow<Result<List<Jokes>>>> {

    override suspend fun run(): Flow<Result<List<Jokes>>> {
        return repository.getJokesList()
    }
}