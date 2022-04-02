package com.lillydoo.jokes.list

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.interactors.GetJokesUseCase
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals

import org.junit.Test

import com.lillydoo.jokes.utils.BaseUnitTest
import com.lillydoo.jokes.viewmodel.JokesListViewModel
import com.lillydoo.shared.result.data
import com.lillydoo.shared.result.exception
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import java.lang.RuntimeException


@ExperimentalCoroutinesApi
class JokesListViewModelTest : BaseUnitTest() {

    private val usecase: GetJokesUseCase = mock()
    private val jokes = mock<List<Jokes>>()
    private val exception = RuntimeException("something went wrong")


    @Test
    fun `should get jokes list from use case`() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getJokesList()

        verify(usecase, times(1)).run()
    }

    @Test
    fun `should emit jokes list from use case`() = runTest {
        val viewModel = mockSuccessfulCase()

        viewModel.getJokesList()

        viewModel.viewState.test {
            assertEquals(jokes, viewModel.viewState.value.data)
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `should emit error when receive error`() = runTest {
        val viewModel = mockFailureCase()

        viewModel.getJokesList()

        viewModel.viewState.test {
            assertEquals(exception, viewModel.viewState.value.exception)
            cancelAndIgnoreRemainingEvents()
        }
    }

    private fun mockSuccessfulCase(): JokesListViewModel {
        runTest {
            whenever(usecase.run()).thenReturn(
                flow {
                    emit(Result.success(jokes))
                }
            )
        }
        return JokesListViewModel(usecase)
    }

    private fun mockFailureCase(): JokesListViewModel {
        runTest {
            whenever(usecase.run()).thenReturn(
                flow {
                    emit(Result.failure(exception))
                }
            )
        }

        return JokesListViewModel(usecase)
    }
}