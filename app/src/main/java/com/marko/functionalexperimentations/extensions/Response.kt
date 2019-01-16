package com.marko.functionalexperimentations.extensions

import com.marko.functionalexperimentations.exceptions.*
import retrofit2.Response

val <F> Response<F>.coinsException: CoinException
	get() =
		when (code()) {
			400 -> BadCoinsRequest
			401 -> UnauthorizedCoinRequest
			404 -> CoinsNotFound
			else -> UnknownCoinException(message())
		}