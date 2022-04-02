package com.lillydoo.shared.utils

interface DomainMapper <T, DomainModel>{
    fun mapToDomainModel(model: T): DomainModel
}