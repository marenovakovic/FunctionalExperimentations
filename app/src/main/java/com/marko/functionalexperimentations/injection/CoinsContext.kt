package com.marko.functionalexperimentations.injection

import com.marko.functionalexperimentations.data.CoinsService
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

data class CoinsContext(
	val scope: CoroutineScope,
	val context: CoroutineContext,
	val coinsService: CoinsService
)