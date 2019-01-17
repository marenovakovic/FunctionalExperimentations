package com.marko.functionalexperimentations.data

import arrow.Kind
import arrow.effects.DeferredK
import arrow.effects.scope
import arrow.effects.typeclasses.MonadDefer
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinId
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

interface CoinsRepository<F> : CoinsRemoteSource<F>, CoinsCacheSource<F>, MonadDefer<F> {

	val coroutineContext: CoroutineContext

	fun getCoins(coinsService: CoinsService): Kind<F, List<Coin>> =
		defer(ctx = coroutineContext) {
			val result = fetchCoins()

			(result as DeferredK).scope().launch {
				repeat(5) {
					delay(1_000)
					println("$it ${coroutineContext[Job]?.key}")
				}
			}

			result
		}.handleErrorWith { queryCoins() }

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