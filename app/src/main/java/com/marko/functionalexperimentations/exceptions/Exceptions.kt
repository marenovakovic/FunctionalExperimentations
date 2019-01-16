package com.marko.functionalexperimentations.exceptions

import arrow.core.None
import arrow.core.Option
import arrow.core.Some

sealed class CoinException(message: Option<String> = None) : Throwable(message.orNull())

object CoinsNotFound : CoinException()

object UnauthorizedCoinRequest : CoinException()

object BadCoinsRequest : CoinException()

class UnknownCoinException(message: String) : CoinException(Some(message))