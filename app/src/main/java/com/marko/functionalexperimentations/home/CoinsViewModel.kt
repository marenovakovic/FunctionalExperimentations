package com.marko.functionalexperimentations.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import arrow.effects.ForDeferredK
import arrow.effects.fix
import arrow.effects.unsafeRunAsync
import com.marko.functionalexperimentations.base.BaseViewModel
import com.marko.functionalexperimentations.data.CoinsRepository
import com.marko.functionalexperimentations.data.CoinsService
import com.marko.functionalexperimentations.dispatchers.CoroutineDispatchers
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.usecases.FetchCoins
import kotlinx.coroutines.launch
import javax.inject.Inject

class CoinsViewModel @Inject constructor(
	dispatchers: CoroutineDispatchers,
	private val coinsRepository: CoinsRepository<ForDeferredK>,
	private val coinsService: CoinsService
) : BaseViewModel(dispatchers) {

	private val _coins = MutableLiveData<List<Coin>>()
	val coins: LiveData<List<Coin>>
		get() = _coins

	private val _error = MutableLiveData<Throwable>()
	val error: LiveData<Throwable>
		get() = _error

	fun fetch() = fetchCoins(coinsRepository, coinsService)

	private fun fetchCoins(repository: CoinsRepository<ForDeferredK>, coinsService: CoinsService) {
		launch {
			FetchCoins(repository, coinsService).fix()
				.unsafeRunAsync { it.fold(_error::postValue, _coins::postValue) }
		}
	}
}