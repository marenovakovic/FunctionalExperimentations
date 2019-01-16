package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.effects.typeclasses.MonadDefer
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinId
import kotlin.coroutines.CoroutineContext

interface CoinsRepository<F> : CoinsRemoteSource<F>, CoinsCacheSource<F>, MonadDefer<F> {

	val coroutineContext: CoroutineContext

	fun getCoins(coinsService: CoinsService): Kind<F, List<Coin>> =
		defer(ctx = coroutineContext) { fetchCoins() }.handleErrorWith { queryCoins() }

	fun getCoin(coinId: CoinId): Kind<F, Coin> =
		defer(ctx = coroutineContext) {
			bindingCatch {
				val cachedCoin = queryCoin(coinId = coinId).bind()
				if (cachedCoin.name.isNotEmpty()) {
					return@bindingCatch cachedCoin
				}

				val fetchedCoin = fetchCoin(coinId = coinId).bind()
				saveCoin(coin = fetchedCoin).bind()

				fetchedCoin
			}
		}
}