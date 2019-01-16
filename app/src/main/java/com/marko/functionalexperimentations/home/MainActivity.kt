package com.marko.functionalexperimentations.home

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.marko.functionalexperimentations.R
import com.marko.functionalexperimentations.base.BaseActivity

class MainActivity : BaseActivity() {

	private val viewModel: CoinsViewModel by lazy(LazyThreadSafetyMode.NONE) {
		ViewModelProviders.of(this, factory).get(CoinsViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel.fetch()
		viewModel.coins.observe(this, Observer { println(it); viewModel.fetchDetails() })
		viewModel.error.observe(this, Observer { it.printStackTrace() })
	}
}