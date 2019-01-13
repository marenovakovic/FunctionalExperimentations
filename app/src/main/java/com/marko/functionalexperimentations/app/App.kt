package com.marko.functionalexperimentations.app

import com.marko.functionalexperimentations.injection.application.applicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {
	private val component = applicationComponent

	override fun applicationInjector(): AndroidInjector<out DaggerApplication> = component
}