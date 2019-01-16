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
	): CoinsResponse = json.run {
		CoinsResponse(
			coins = asJsonObject.getAsJsonArray("data").map {
				it.asJsonObject.run {
					Coin(
						id = get("id").asInt,
						name = get("name").asString,
						symbol = get("symbol").asString
					)
				}
			},
			status = asJsonObject.getAsJsonObject("status").run {
				CoinStatus(
					timestamp = get("timestamp").asString,
					errorCode = get("error_code").asInt,
					elapsed = get("elapsed").asInt,
					creditCount = get("credit_count").asInt
				)
			}
		)
	}
}