package com.github.gabrielrechbrand.`interface`.controller

import java.time.LocalDate

data class CreateTaskRequest(
    val title: String,
    val dueDate: LocalDate?
)
