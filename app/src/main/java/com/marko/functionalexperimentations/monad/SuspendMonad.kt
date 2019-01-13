package com.marko.functionalexperimentations.monad

import arrow.core.Either
import arrow.core.Right
import arrow.core.flatMap

suspend fun <E, A> Either<E, A>.suspend(): Either<E, A> {
	return this.apply {
		map { 4 }
		flatMap { Right(4) }
		3
	}
}