package com.marko.functionalexperimentations.extensions

import arrow.core.Either
import arrow.core.Try

suspend fun <A> runSafe(f: suspend () -> A): Either<Throwable, A> =
	Try { f() }.toEither()