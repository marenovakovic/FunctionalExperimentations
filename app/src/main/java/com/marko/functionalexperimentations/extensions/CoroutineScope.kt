package com.marko.functionalexperimentations.extensions

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

val exceptionHandler = CoroutineExceptionHandler { _, t ->
	t.printStackTrace()
}

fun <T> CoroutineScope.actorConsume(action: (T) -> Unit) = actor<T> {
	consumeEach(action)
}