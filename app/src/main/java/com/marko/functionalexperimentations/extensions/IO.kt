package com.marko.functionalexperimentations.extensions

import arrow.core.Either
import arrow.effects.IO
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

suspend fun <A> IO<A>.await(): Either<Throwable, A> =
	suspendCoroutine { unsafeRunAsync(it::resume) }

suspend fun <A> IO<A>.awaitSync(): A =
	suspendCoroutine { it.resume(unsafeRunSync()) }

suspend fun <A> IO<A>.awaitIO(): IO<A> =
	suspendCoroutine { it.resume(this) }