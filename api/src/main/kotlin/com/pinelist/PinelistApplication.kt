package com.pinelist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PinelistApplication

fun main(args: Array<String>) {
    runApplication<PinelistApplication>(*args)
}
