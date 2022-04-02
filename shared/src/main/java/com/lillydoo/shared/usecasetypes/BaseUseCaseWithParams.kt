package com.lillydoo.shared.usecasetypes

interface BaseUseCaseWithParams<P, R> {
    suspend fun run(params : P) : R
}