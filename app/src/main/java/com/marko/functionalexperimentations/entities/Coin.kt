package com.marko.functionalexperimentations.entities

typealias CoinId = Int

data class Coin(
	val id: Int,
	val name: String,
	val symbol: String
)