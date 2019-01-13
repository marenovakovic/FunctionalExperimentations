package com.marko.functionalexperimentations.data

import arrow.core.Either
import arrow.core.Right
import com.marko.functionalexperimentations.entities.Coin

interface CoinsCacheSource {

	companion object {
		suspend fun queryCoins(): Either<Throwable, List<Coin>> = Right(listOf())
	}
}