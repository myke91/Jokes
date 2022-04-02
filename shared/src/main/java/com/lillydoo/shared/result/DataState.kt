/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lillydoo.shared.result

import androidx.lifecycle.MutableLiveData

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class DataState<out R> {

    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
        }
    }
}


/**
 * `true` if [DataState] is of type [Success] & holds non-null [Success.data].
 */
val DataState<*>.succeeded
    get() = this is DataState.Success && data != null

fun <T> DataState<T>.successOr(fallback: T): T {
    return (this as? DataState.Success<T>)?.data ?: fallback
}

val <T> DataState<T>.data: T?
    get() = (this as? DataState.Success)?.data

val <T> DataState<T>.exception: Exception?
    get() = (this as? DataState.Error)?.exception

/**
 * Updates value of [liveData] if [DataState] is of type [Success]
 */
inline fun <reified T> DataState<T>.updateOnSuccess(liveData: MutableLiveData<T>) {
    if (this is DataState.Success) {
        liveData.value = data
    }
}

