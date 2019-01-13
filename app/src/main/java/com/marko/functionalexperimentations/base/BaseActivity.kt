package com.marko.functionalexperimentations.base

import com.marko.functionalexperimentations.injection.viewmodel.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class BaseActivity : DaggerAppCompatActivity(), CoroutineScope {

	@Inject
	lateinit var factory: ViewModelFactory

	private val job = Job()

	override val coroutineContext: CoroutineContext
		get() = Dispatchers.Main + job

	override fun onDestroy() {
		super.onDestroy()

		job.cancel()
	}
}