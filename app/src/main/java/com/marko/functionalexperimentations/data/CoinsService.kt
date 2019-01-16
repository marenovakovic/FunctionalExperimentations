package com.marko.functionalexperimentations.data

import arrow.integrations.retrofit.adapter.CallK
import arrow.integrations.retrofit.adapter.CallKindAdapterFactory
import com.google.gson.GsonBuilder
import com.marko.functionalexperimentations.entities.CoinId
import com.marko.functionalexperimentations.entities.CoinResponse
import com.marko.functionalexperimentations.entities.CoinsResponse
import com.marko.functionalexperimentations.gson.CoinResponseDeserializer
import com.marko.functionalexperimentations.gson.CoinsResponseDeserializer
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

private const val API_KEY = "4312a484-4097-4991-8f28-f02e77bca15b"

interface CoinsService {

	@GET("cryptocurrency/listings/latest")
	fun getCoins(@Query("CMC_PRO_API_KEY") apiKey: String = API_KEY): CallK<CoinsResponse>

	@GET("cryptocurrency/info")
	fun getCoinDetails(
		@Query("CMC_PRO_API_KEY") apiKey: String = API_KEY,
		@Query("id") coinId: CoinId
	): CallK<CoinResponse>
}

private const val READ_TIMEOUT = 15L

private const val WRITE_TIMEOUT = 15L

private val okHttpClient = OkHttpClient.Builder()
	.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
	.writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
	.build()

private val gson =
	GsonBuilder()
		.apply {
			registerTypeAdapter(CoinsResponse::class.java, CoinsResponseDeserializer)
			registerTypeAdapter(CoinResponse::class.java, CoinResponseDeserializer)
		}
		.create()

private val retrofit = Retrofit.Builder()
	.client(okHttpClient)
	.baseUrl("https://pro-api.coinmarketcap.com/v1/")
	.addConverterFactory(GsonConverterFactory.create(gson))
	.addCallAdapterFactory(CallKindAdapterFactory())
	.build()

val coinsService = retrofit.create(CoinsService::class.java)