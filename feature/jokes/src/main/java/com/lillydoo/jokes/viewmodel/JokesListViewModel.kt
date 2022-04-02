package com.lillydoo.jokes.viewmodel

import androidx.lifecycle.*
import com.lillydoo.jokes.data.model.Jokes
import com.lillydoo.jokes.interactors.GetJokesUseCase
import com.lillydoo.shared.result.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class JokesListViewModel @Inject constructor(
    private val useCase: GetJokesUseCase,
) : ViewModel() {
    private val _viewState = MutableStateFlow<DataState<List<Jokes>>>(
        DataState.Success(emptyList())
    )
    val viewState: StateFlow<DataState<List<Jokes>>> get() = _viewState


    fun getJokesList() {
        viewModelScope.launch {
            _viewState.value = DataState.Loading
            try {
                useCase.run().collectLatest {
                    if (it.isSuccess) {
                        _viewState.value =
                            DataState.Success(it.getOrDefault(emptyList()))
                    } else {
                        _viewState.value =
                            DataState.Error(it.exceptionOrNull() as Exception)
                    }
                }
            } catch (e: Exception) {
                _viewState.value = DataState.Error(e)
            }
        }
    }
}
