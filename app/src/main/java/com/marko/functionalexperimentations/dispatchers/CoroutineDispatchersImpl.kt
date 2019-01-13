package com.marko.functionalexperimentations.dispatchers

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

class CoroutineDispatchersImpl @Inject constructor() : CoroutineDispatchers {

	override val main: CoroutineContext
		get() = Dispatchers.Main
	override val io: CoroutineContext
		get() = Dispatchers.IO
	override val default: CoroutineContext
		get() = Dispatchers.Default
}