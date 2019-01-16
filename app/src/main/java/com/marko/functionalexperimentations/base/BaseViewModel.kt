package com.marko.functionalexperimentations.base

import androidx.lifecycle.ViewModel
import arrow.core.Either
import arrow.effects.DeferredK
import arrow.effects.unsafeRunAsync
import kotlinx.coroutines.*

private val exceptionHandler = CoroutineExceptionHandler { context, t ->
	t.printStackTrace()
}

abstract class BaseViewModel : ViewModel(), CoroutineScope by MainScope() + exceptionHandler {

	private val jobs = mutableListOf<Job>()

	fun <A> DeferredK<A>.runJob(callback: (Either<Throwable, A>) -> Unit) {
		jobs.add(this)
		unsafeRunAsync { callback(it) }
	}

	override fun onCleared() {
		super.onCleared()

		jobs.forEach { it.cancel() }
		cancel()
	}
}