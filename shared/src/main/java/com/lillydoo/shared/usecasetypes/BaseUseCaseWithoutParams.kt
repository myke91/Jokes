package com.lillydoo.shared.usecasetypes

interface BaseUseCaseWithoutParams<R> {
    suspend fun run() : R
}