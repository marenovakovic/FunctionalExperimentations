package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinId
import com.marko.functionalexperimentations.extensions.coinsException

interface CoinsRemoteSource<F> : Async<F> {
	val coinsService: CoinsService

	fun fetchCoins(): Kind<F, List<Coin>> =
		coinsService.getCoins().async(this).flatMap {
			if (it.isSuccessful) {
				just(it.body() !!.coins)
			} else {
				raiseError(it.coinsException)
			}
		}

	fun fetchCoin(coinId: CoinId): Kind<F, Coin> =
		coinsService.getCoinDetails(coinId = coinId).async(this).flatMap {
			if (it.isSuccessful) {
				just(it.body() !!.coin)
			} else {
				raiseError(it.coinsException)
			}
		}
}