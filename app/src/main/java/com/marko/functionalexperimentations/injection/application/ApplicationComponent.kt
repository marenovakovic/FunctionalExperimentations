package com.marko.functionalexperimentations.injection.application

import com.marko.functionalexperimentations.app.App
import com.marko.functionalexperimentations.app.AppModule
import com.marko.functionalexperimentations.injection.activity.ActivityBindingModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(
	modules = [
		AndroidInjectionModule::class,
		AndroidSupportInjectionModule::class,
		AppModule::class,
		ActivityBindingModule::class
	]
)
interface ApplicationComponent : AndroidInjector<App>

val App.applicationComponent: ApplicationComponent
	get() =
		DaggerApplicationComponent.builder()
			.appModule(AppModule(this))
			.build()