package com.marko.functionalexperimentations.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

private val exceptionHandler = CoroutineExceptionHandler { _, t ->
	t.printStackTrace()
}

abstract class BaseViewModel : ViewModel(), CoroutineScope by MainScope() + exceptionHandler {

	override fun onCleared() {
		super.onCleared()

		cancel()
		println("${coroutineContext[Job]?.key} canceled")
	}
}