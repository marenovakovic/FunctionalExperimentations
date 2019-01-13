package com.marko.functionalexperimentations.extensions

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach

fun <T> CoroutineScope.actorConsume(action: (T) -> Unit) = actor<T> {
	consumeEach(action)
}