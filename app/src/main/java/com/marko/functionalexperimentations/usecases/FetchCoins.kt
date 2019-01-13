package com.marko.functionalexperimentations.usecases

import arrow.Kind
import com.marko.functionalexperimentations.data.CoinsRepository
import com.marko.functionalexperimentations.data.CoinsService
import com.marko.functionalexperimentations.entities.Coin

object FetchCoins {

	operator fun <F> invoke(
		repository: CoinsRepository<F>,
		coinsService: CoinsService
	): Kind<F, List<Coin>> = repository.getCoins(coinsService)
}