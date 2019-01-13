package com.marko.functionalexperimentations.usecases

interface UseCase<in P, R> {

	operator fun invoke(parameters: P): R
}