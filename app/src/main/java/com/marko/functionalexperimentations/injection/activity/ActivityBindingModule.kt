package com.marko.functionalexperimentations.injection.activity

import com.marko.functionalexperimentations.home.HomeModule
import com.marko.functionalexperimentations.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

	@ActivityScope
	@ContributesAndroidInjector(modules = [HomeModule::class])
	abstract fun mainActivity(): MainActivity
}