package com.marko.functionalexperimentations.app

import android.content.Context
import com.marko.functionalexperimentations.data.CoinsService
import com.marko.functionalexperimentations.data.coinsService
import com.marko.functionalexperimentations.dispatchers.CoroutineDispatchers
import com.marko.functionalexperimentations.dispatchers.CoroutineDispatchersImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppBindingModule {

	@Binds
	abstract fun dispatchers(bind: CoroutineDispatchersImpl): CoroutineDispatchers
}

@Module(includes = [AppBindingModule::class])
class AppModule(private val context: Context) {

	@Provides
	fun context(): Context = context

	@Provides
	fun coinsService(): CoinsService = coinsService
}