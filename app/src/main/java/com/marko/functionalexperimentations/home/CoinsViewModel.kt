package com.marko.functionalexperimentations.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.effects.DeferredK
import arrow.effects.ForDeferredK
import arrow.effects.deferredk.async.async
import arrow.effects.fix
import arrow.effects.typeclasses.Async
import arrow.effects.unsafeRunAsync
import com.marko.functionalexperimentations.base.BaseViewModel
import com.marko.functionalexperimentations.data.CoinsRepository
import com.marko.functionalexperimentations.data.CoinsService
import com.marko.functionalexperimentations.entities.Coin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class CoinsViewModel @Inject constructor(
	private val coinsService: CoinsService
) : BaseViewModel() {

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val _error = MutableLiveData<Throwable>()
	val error: LiveData<Throwable>
		get() = _error

	// TODO what to do with you?
	private val coinsRepository =
		object : CoinsRepository<ForDeferredK>, Async<ForDeferredK> by DeferredK.async() {
			override val coroutineContext: CoroutineContext
				get() = this@CoinsViewModel.coroutineContext

			override val coinsService: CoinsService
				get() = this@CoinsViewModel.coinsService
		}

	fun fetch() {
		launch {
			coinsRepository.getCoins(coinsService).fix()
				.unsafeRunAsync { it.fold(_error::postValue, _coins::postValue) }
		}

		launch {
			repeat(5) {
				delay(1_000)
				println("$it viewModel")
			}
		}
	}

	fun fetchDetails() {
		launch {
			coinsRepository.getCoin(coinId = 1).fix()
				.unsafeRunAsync { it.fold({ it.printStackTrace() }, { println(it) }) }
		}
	}

//	fun <F, TC> TC.findUserName(id: Kind<F, String>)
//			: Kind<F, String> where TC : Applicative<F>, TC : Number =
//		id.map { listOf<String>() }.map { "" }
}