package com.marko.functionalexperimentations

import org.junit.Test

class ExampleUnitTest {

	@Test
	fun nothing() {
		val c = Class(name = "Marko", username = "Marko")
		println(c)
		val c2  = c.copy(username = "Najveci car na svetu")
		println(c2)
	}

	data class Class(
		val name: String,
		val username: String
	)
}
