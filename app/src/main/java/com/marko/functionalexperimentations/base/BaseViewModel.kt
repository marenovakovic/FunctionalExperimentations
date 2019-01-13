package com.marko.functionalexperimentations.base

import androidx.lifecycle.ViewModel
import com.marko.functionalexperimentations.dispatchers.CoroutineDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel(
	private val dispatchers: CoroutineDispatchers
) : ViewModel(), CoroutineScope {

	private var job = Job()

	override val coroutineContext: CoroutineContext
		get() = dispatchers.io + job

	protected fun runJob(job: Job) {
		this.job = job
	}

	override fun onCleared() {
		super.onCleared()

		job.cancel()
	}
}