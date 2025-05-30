package com.github.gabrielrechbrand

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CleanArchitectureApplication

fun main(args: Array<String>) {
    runApplication<CleanArchitectureApplication>(*args)
}