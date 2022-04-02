package com.lillydoo.jokes.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@ExperimentalCoroutinesApi
open class BaseUnitTest {
    @get:Rule
    val coroutineRule = MainCoroutineScopeRule()
}