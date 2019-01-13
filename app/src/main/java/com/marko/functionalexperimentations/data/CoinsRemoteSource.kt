package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.effects.typeclasses.Async
import com.marko.functionalexperimentations.entities.Coin

interface CoinsRemoteSource<F> : Async<F> {

	fun fetchCoins(coinsService: CoinsService): Kind<F, List<Coin>> =
		coinsService.getCoins().async(this).map { it.body() !!.coins }
}