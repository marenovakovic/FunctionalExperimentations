package com.marko.functionalexperimentations.entities

data class CoinsResponse(
	val coins: List<Coin>,
	val status: CoinStatus
)