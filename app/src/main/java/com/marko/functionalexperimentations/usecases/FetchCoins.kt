package com.marko.functionalexperimentations.usecases

import arrow.Kind
import com.marko.functionalexperimentations.data.CoinsRepository
import com.marko.functionalexperimentations.data.CoinsService
import com.marko.functionalexperimentations.entities.Coin
import javax.inject.Inject

class FetchCoins<F> @Inject constructor(
	private val coinsService: CoinsService
) {
	operator fun invoke(coinsRepository: CoinsRepository<F>): Kind<F, List<Coin>> =
		coinsRepository.getCoins(coinsService)
}