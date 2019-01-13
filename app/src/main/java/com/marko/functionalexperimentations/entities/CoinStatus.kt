package com.marko.functionalexperimentations.entities

data class CoinStatus(
	val timestamp: String = "",
	val errorCode: Int = -1,
	val elapsed: Int = -1,
	val creditCount: Int = -1
)