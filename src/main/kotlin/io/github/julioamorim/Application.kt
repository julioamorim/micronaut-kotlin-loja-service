package io.github.julioamorim

import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	build()
	    .args(*args)
		.packages("io.github.julioamorim")
		.start()
}

