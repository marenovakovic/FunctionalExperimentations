package com.marko.functionalexperimentations.dispatchers

import kotlin.coroutines.CoroutineContext

interface CoroutineDispatchers {

	val main: CoroutineContext

	val io: CoroutineContext

	val default: CoroutineContext
}