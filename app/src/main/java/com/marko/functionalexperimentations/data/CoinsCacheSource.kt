package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.core.Right
import arrow.effects.typeclasses.Async
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinId

interface CoinsCacheSource<F> : Async<F> {

	fun queryCoins(): Kind<F, List<Coin>> = async { it(Right(listOf())) }

	fun queryCoin(coinId: CoinId): Kind<F, Coin> = async { it(Right(Coin(id = 1, name = "", symbol = ""))) }

	fun saveCoin(coin: Coin): Kind<F, Unit> = async { it(Right(Unit)) }
}