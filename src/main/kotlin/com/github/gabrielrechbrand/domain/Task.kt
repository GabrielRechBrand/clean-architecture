package com.github.gabrielrechbrand.domain

import java.time.LocalDate

data class Task(
    val id: Long? = null,
    val title: String,
    val dueDate: LocalDate? = null,
    val completed: Boolean = false
)
