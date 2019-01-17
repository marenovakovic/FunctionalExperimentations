package com.marko.functionalexperimentations.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.marko.functionalexperimentations.injection.viewmodel.ViewModelFactory
import com.marko.functionalexperimentations.injection.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class HomeBindingModule {

	@Binds
	@IntoMap
	@ViewModelKey(CoinsViewModel::class)
	abstract fun coinsViewModule(bind: CoinsViewModel): ViewModel

	@Binds
	abstract fun factory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}

@Module(includes = [HomeBindingModule::class])
class HomeModule {


}