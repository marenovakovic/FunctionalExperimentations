package com.marko.functionalexperimentations

import kotlinx.coroutines.*
import org.junit.Test

class ExampleUnitTest {

	@Test
	fun nothing() = runBlocking {
		var a = A()
		println(a.hashCode())

		val scope: CoroutineScope = CoroutineScope(Dispatchers.Unconfined + Job())

		val value = scope.async { a.hashCode() }

		println(value.await())

		a = A()
		println(a.hashCode())

		println(value.await())
	}

	class A
}
