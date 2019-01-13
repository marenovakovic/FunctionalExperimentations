package com.marko.functionalexperimentations.gson

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.marko.functionalexperimentations.entities.Coin
import com.marko.functionalexperimentations.entities.CoinStatus
import com.marko.functionalexperimentations.entities.CoinsResponse
import java.lang.reflect.Type

object CoinsResponseDeserializer : JsonDeserializer<CoinsResponse> {

	override fun deserialize(
		json: JsonElement,
		typeOfT: Type,
		context: JsonDeserializationContext
	): CoinsResponse {
		val data = json.asJsonObject.getAsJsonArray("data")

		val coins = data.map {
			it.asJsonObject.run {
				Coin(
					id = get("id").asInt,
					name = get("name").asString,
					symbol = get("symbol").asString
				)
			}
		}

		val statusJson = json.asJsonObject.getAsJsonObject("status")

		val status = CoinStatus(
			timestamp = statusJson["timestamp"].asString,
			errorCode = statusJson["error_code"].asInt,
			elapsed = statusJson["elapsed"].asInt,
			creditCount = statusJson["credit_count"].asInt
		)

		return CoinsResponse(coins = coins, status = status)
	}
}