package com.lillydoo.jokes.list

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.lillydoo.jokes.data.JokesListRepository
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.data.model.JokesDTO
import com.lillydoo.jokes.data.source.remote.JokesListRemoteDatasource
import com.lillydoo.jokes.domain.JokesMapper
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.flow
import org.junit.Test
import com.lillydoo.jokes.utils.BaseUnitTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import java.lang.RuntimeException


@ExperimentalCoroutinesApi
class JokesListRepositoryTest : BaseUnitTest() {

    private val remoteDatasource: JokesListRemoteDatasource = mock()
    private val jokes = mock<List<Jokes>>()
    private val exception = RuntimeException("Unable to get results from API")
    private val jokesDTO = mock<List<JokesDTO>>()
    private val mapper: JokesMapper = mock()

    @Test
    fun `should get list of jokes from datasource`() = runTest {
        val repository = mockSuccessfulCase()

        repository.getJokesList()

        verify(remoteDatasource, times(1)).fetchJokes()
    }

    @Test
    fun `should emit mapped jokes list from datasource`() = runTest {
        val repository = mockSuccessfulCase()

        assertEquals(jokes, repository.getJokesList().first().getOrNull()!!)
    }


    @Test
    fun `should propagate errors`() = runTest {
        val repository = mockFailureCase()

        assertEquals(exception, repository.getJokesList().first().exceptionOrNull())
    }


    private suspend fun mockSuccessfulCase(): JokesListRepository {
        whenever(remoteDatasource.fetchJokes()).thenReturn(
            flow {
                emit(Result.success(jokesDTO))
            }
        )

        whenever(mapper.toDomainList(jokesDTO)).thenReturn(
            jokes
        )

        return JokesListRepository(remoteDatasource, mapper)
    }

    private suspend fun mockFailureCase(): JokesListRepository {
        whenever(remoteDatasource.fetchJokes()).thenReturn(
            flow {
                emit(Result.failure(exception))
            }
        )

        return JokesListRepository(remoteDatasource, mapper)
    }
}