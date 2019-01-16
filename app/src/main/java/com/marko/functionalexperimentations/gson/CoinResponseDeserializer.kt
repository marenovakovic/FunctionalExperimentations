package com.marko.functionalexperimentations.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinResponse
import com.marko.functionalexperimentations.entities.CoinStatus
import java.lang.reflect.Type

object CoinResponseDeserializer : JsonDeserializer<CoinResponse> {

	override fun deserialize(
		json: JsonElement,
		typeOfT: Type,
		context: JsonDeserializationContext
	): CoinResponse {
		val data = json.asJsonObject.getAsJsonObject("data")
		val id = data.entrySet().first().value
		val coinJson = id.asJsonObject

		val coin = Coin(
			id = coinJson["id"].asInt,
			name = coinJson["name"].asString,
			symbol = coinJson["symbol"].asString
		)

		val statusJson = json.asJsonObject.getAsJsonObject("status")

		val status = CoinStatus(
			timestamp = statusJson["timestamp"].asString,
			errorCode = statusJson["error_code"].asInt,
			elapsed = statusJson["elapsed"].asInt,
			creditCount = statusJson["credit_count"].asInt
		)

		return CoinResponse(coin = coin, status = status)
	}
}