package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.effects.typeclasses.MonadDefer
import com.marko.functionalexperimentations.entities.Coin

interface CoinsRepository<F> : CoinsRemoteSource<F>, CoinsCacheSource, MonadDefer<F> {

	fun getCoins(coinsService: CoinsService): Kind<F, List<Coin>> =
		defer { fetchCoins(coinsService) }
}