package com.github.gabrielrechbrand.infrastructure.persistence

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "task")
data class TaskEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val title: String = "",

    val dueDate: LocalDate? = null
)